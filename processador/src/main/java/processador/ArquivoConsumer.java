package processador;

import app.ProcessadorConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ArquivoConsumer {

    @JmsListener(destination = ProcessadorConfig.ARQUIVO_QUEUE)
    public void receive(String text) {
        System.out.println(text);
    }

}
