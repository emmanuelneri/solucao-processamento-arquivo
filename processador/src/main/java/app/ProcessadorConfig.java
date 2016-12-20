package app;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import processador.ArquivoConsumer;

import javax.jms.Queue;

@SpringBootApplication
@ComponentScan(basePackageClasses = {ArquivoConsumer.class})
@EnableJms
public class ProcessadorConfig {

    public static final String ARQUIVO_QUEUE = "arquivo.queue";

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ProcessadorConfig.class, args);
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(ARQUIVO_QUEUE);
    }
}
