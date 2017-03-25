package br.com.emmanuelneri;

import br.com.emmanuelneri.app.arquivo.ArquivoController;
import br.com.emmanuelneri.app.notafiscal.controller.NotaFiscalController;
import br.com.emmanuelneri.app.notafiscal.service.NotaFiscalService;
import br.com.emmanuelneri.app.notafiscal.model.NotaFiscal;
import br.com.emmanuelneri.app.notafiscal.repository.NotaFiscalRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {NotaFiscal.class, Jsr310JpaConverters.class})
@EnableJpaRepositories(basePackageClasses = {NotaFiscalRepository.class})
@ComponentScan(basePackageClasses = {ArquivoController.class, DisponibilizadorProperties.class, NotaFiscalService.class, NotaFiscalController.class})
public class DisponibilizadorAppConfig {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DisponibilizadorAppConfig.class, args);
    }
}
