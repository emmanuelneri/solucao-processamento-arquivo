package br.com.emmanuelneri.leitor;

import br.com.emmanuelneri.app.LeitorProperties;
import br.com.emmanuelneri.util.FileUtils;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

@Component
public class LeitorArquivoScheduled {

    private Logger LOGGER = LoggerFactory.getLogger(LeitorArquivoScheduled.class);

    @Autowired
    private LeitorProperties properties;

    @Autowired
    private ArquivoSender arquivoSender;

    @Scheduled(fixedRate = 1000000)
    public void importer() throws IOException {
        LOGGER.info("----------- Iniciado Importador de Arquivos ----------");

        final Collection<File> arquivos = FileUtils.getFilesInDirectory(new File(properties.getDiretorioNovo()));

        if(!arquivos.isEmpty()) {
            try (MongoClient mongoClient = new MongoClient(properties.getMongoHost(), properties.getMongoPort())) {

                final MongoDatabase db = mongoClient.getDatabase("arquivo-processamento");
                final MongoCollection<Document> arquivosCollection = db.getCollection("arquivos");

                for (File file : arquivos) {
                    try {
                        final String xml = Files.toString(file, Charsets.UTF_8);

                        inserirArquivo(arquivosCollection, xml);
                        moverArquivoInserido(file);
                        enviarArquivoParaFilaDeProcessamento(xml);
                    } catch (Exception ex) {
                        LOGGER.error("Erro durante inserção do arquivo", ex);
                    }
                }
            }
        } else {
            LOGGER.info("----------- Nenhum arquivo encontrado ----------");
        }
    }

    private void inserirArquivo(MongoCollection<Document> arquivosCollection, String file) throws IOException {
        arquivosCollection.insertOne(new Document("xml", file));
    }

    private void moverArquivoInserido(File file) throws IOException {
        FileUtils.move(file, properties.getDiretorioBkp() + file.getName());
    }

    private void enviarArquivoParaFilaDeProcessamento(String file) {
        arquivoSender.send(file);
    }
}
