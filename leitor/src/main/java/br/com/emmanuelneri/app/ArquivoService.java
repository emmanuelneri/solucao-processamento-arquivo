package br.com.emmanuelneri.app;

import br.com.emmanuelneri.LeitorProperties;
import br.com.emmanuelneri.app.util.FileUtils;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

@Service
@Slf4j
public class ArquivoService {

    @Autowired
    private LeitorProperties properties;

    @Autowired
    private ArquivoSender arquivoSender;

    public void importarArquivosXml() throws IOException {
        final Collection<File> arquivos = FileUtils.getXmlFilesInDirectory(new File(properties.getDiretorioNovo()));

        if(!arquivos.isEmpty()) {
            processarArquivos(arquivos);
        } else {
            log.info("----------- Nenhum arquivo encontrado ----------");
        }
    }

    private void processarArquivos(Collection<File> arquivos) throws IOException {
        for (File arquivo : arquivos) {
            try {
                final String xml = Files.toString(arquivo, Charsets.UTF_8);
                inserirArquivosNaBase(xml);
                moverArquivoParaPastaBkp(arquivo);
                enviarArquivoParaFilaDeProcessamento(xml);
            } catch (Exception ex) {
                moverArquivoParaPastaErro(arquivo);
                log.error("Erro durante inserção do arquivo", ex);
            }
        }
    }

    private void inserirArquivosNaBase(String arquivo) throws IOException {
        final Document arquivoDocument = new Document("xml", arquivo);
        try (MongoClient mongoClient = new MongoClient(properties.getMongoHost(), properties.getMongoPort())) {

            final MongoDatabase db = mongoClient.getDatabase(properties.getMongoDataBase());
            final MongoCollection<Document> arquivosCollection = db.getCollection("arquivos");

            arquivosCollection.insertOne(arquivoDocument);
        }
    }

    private void moverArquivoParaPastaBkp(File file) throws IOException {
        FileUtils.move(file, properties.getDiretorioBkp() + file.getName());
    }

    private void moverArquivoParaPastaErro(File file) throws IOException {
        FileUtils.move(file, properties.getDiretorioErro() + file.getName());
    }

    private void enviarArquivoParaFilaDeProcessamento(String file) {
        arquivoSender.send(file);
    }

}
