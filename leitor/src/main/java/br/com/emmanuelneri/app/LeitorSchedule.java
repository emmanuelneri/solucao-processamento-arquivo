package br.com.emmanuelneri.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class LeitorSchedule {

    @Autowired
    private ArquivoService arquivoService;

    @Scheduled(fixedRate = 1000000)
    public void importar() throws IOException {
        log.info("----------- Iniciado Importador de Arquivos ----------");
        arquivoService.importarArquivosXml();
        log.info("----------- Finalizando Importador de Arquivos ----------");

    }

}
