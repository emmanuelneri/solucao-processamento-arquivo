package br.com.emmanuelneri.app.component;

import br.com.emmanuelneri.app.service.NotaFiscalXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.Message;

@MessageEndpoint
public class NotaFiscalHandler {

    @Autowired
    private NotaFiscalXmlService notaFiscalXmlService;

    @Autowired
    private ArquivoNotaFiscalSender arquivoNotaFiscalSender;

    public void handle(Message<Object> message) {
        notaFiscalXmlService.salvar(message);
        arquivoNotaFiscalSender.send(message.getPayload());
    }
}