package br.com.emmanuelneri.app.service;

import br.com.emmanuelneri.LeitorProperties;
import br.com.emmanuelneri.app.component.ArquivoNotaFiscalSender;
import br.com.emmanuelneri.app.util.FileUtils;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
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
    private ArquivoNotaFiscalSender arquivoNotaFiscalSender;

    @Autowired
    private NotaFiscalXmlService notaFiscalXmlService;

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
                inserirArquivosNaBase(arquivo.getName(), xml);
                enviarArquivoParaFilaDeProcessamento(xml);
                moverArquivoParaPastaBkp(arquivo);
            } catch (Exception ex) {
                moverArquivoParaPastaErro(arquivo);
                log.error("Erro durante inserção do arquivo", ex);
            }
        }
    }

    private void inserirArquivosNaBase(String nomeArquivo, String xml) {
        notaFiscalXmlService.salvar(nomeArquivo, xml);
    }

    private void moverArquivoParaPastaBkp(File file) throws IOException {
        FileUtils.move(file, properties.getDiretorioBkp() + file.getName());
    }

    private void moverArquivoParaPastaErro(File file) throws IOException {
        FileUtils.move(file, properties.getDiretorioErro() + file.getName());
    }

    private void enviarArquivoParaFilaDeProcessamento(String file) {
        arquivoNotaFiscalSender.send(file);
    }

}
