package processador;

import app.ProcessadorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import processador.service.NotaFiscalService;

@Component
public class ArquivoConsumer {

    private Logger LOGGER = LoggerFactory.getLogger(ArquivoConsumer.class);

    @Autowired
    private NotaFiscalService notaFiscalService;

    @JmsListener(destination = ProcessadorConfig.ARQUIVO_QUEUE)
    public void receive(String arquivo) {
        LOGGER.debug("Iniciando processamento arquivo");
        notaFiscalService.processar(arquivo);
        LOGGER.debug("Finalizando processamento arquivo");
    }

}
