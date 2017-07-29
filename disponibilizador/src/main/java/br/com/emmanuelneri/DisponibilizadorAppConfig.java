package br.com.emmanuelneri;

import br.com.emmanuelneri.app.arquivo.controller.ArquivoNotaFiscalController;
import br.com.emmanuelneri.app.notafiscal.controller.NotaFiscalController;
import br.com.emmanuelneri.app.notafiscal.repository.NotaFiscalRepository;
import br.com.emmanuelneri.app.notafiscal.service.NotaFiscalService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {ArquivoNotaFiscalController.class, DisponibilizadorProperties.class, NotaFiscalRepository.class, NotaFiscalService.class, NotaFiscalController.class})
@EnableEurekaClient
public class DisponibilizadorAppConfig {

    public static void main(String[] args) {
        SpringApplication.run(DisponibilizadorAppConfig.class, args);
    }
}
