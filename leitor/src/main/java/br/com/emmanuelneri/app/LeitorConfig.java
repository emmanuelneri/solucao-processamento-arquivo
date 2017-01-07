package br.com.emmanuelneri.app;

import br.com.emmanuelneri.leitor.LeitorArquivoScheduled;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.Queue;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackageClasses = {LeitorArquivoScheduled.class, LeitorProperties.class})
@EnableJms
public class LeitorConfig {

    public static final String ARQUIVO_QUEUE = "arquivo.queue";

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(LeitorConfig.class, args);
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(ARQUIVO_QUEUE);
    }
}
