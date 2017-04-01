package br.com.emmanuelneri.app;

import br.com.emmanuelneri.ProcessadorAppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import br.com.emmanuelneri.app.notafiscal.service.NotaFiscalService;

@Component
public class ArquivoNotaFiscalConsumer {

    private Logger LOGGER = LoggerFactory.getLogger(ArquivoNotaFiscalConsumer.class);

    @Autowired
    private NotaFiscalService notaFiscalService;

    @JmsListener(destination = ProcessadorAppConfig.NOTA_FISCALQUEUE)
    public void receive(String xml) {
        LOGGER.debug("Iniciando processamento arquivo");
        notaFiscalService.processar(xml);
        LOGGER.debug("Finalizando processamento arquivo");
    }

}
