package br.com.emmanuelneri;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.support.Transformers;

import javax.jms.Queue;
import java.io.File;

@SpringBootApplication
@EnableIntegration
@EnableDiscoveryClient
public class LeitorAppConfig {

    private static final String ARQUIVO_QUEUE = "nota.fiscal.queue";

    @Autowired
    private ArquivosPropertiesConfig leitorProperties;

    public static void main(String[] args) {
        SpringApplication.run(LeitorAppConfig.class, args);
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(ARQUIVO_QUEUE);
    }

    @Bean
    public IntegrationFlow fileReadingFlow() {
        return IntegrationFlows
                .from(s -> s.file(new File(leitorProperties.getDiretorio()))
                                .patternFilter("*.xml")
                                .nioLocker(),
                        e -> e.poller(Pollers.fixedDelay(1000)))
                .transform(Transformers.fileToString())
                .handle("notaFiscalHandler","handle")
                .get();
    }
}
