package br.com.emmanuelneri;

import br.com.emmanuelneri.app.notafiscal.repository.NotaFiscalRepository;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import br.com.emmanuelneri.app.ArquivoNotaFiscalConsumer;
import br.com.emmanuelneri.app.notafiscal.model.NotaFiscal;

import javax.jms.Queue;

@SpringBootApplication
@ComponentScan(basePackageClasses = {ArquivoNotaFiscalConsumer.class})
@EntityScan(basePackageClasses = {NotaFiscal.class, Jsr310JpaConverters.class})
@EnableJpaRepositories(basePackageClasses = {NotaFiscalRepository.class})
@EnableJms
public class ProcessadorAppConfig {

    public static final String NOTA_FISCALQUEUE = "nota.fiscal.queue";
    public static final String NOTA_FISCAL_ERRO_QUEUE = "nota.fiscal.erro.queue";

    public static void main(String[] args) {
        SpringApplication.run(ProcessadorAppConfig.class, args);
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(NOTA_FISCAL_ERRO_QUEUE);
    }
}
