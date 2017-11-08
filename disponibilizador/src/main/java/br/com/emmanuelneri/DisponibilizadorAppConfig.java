package br.com.emmanuelneri;

import br.com.emmanuelneri.app.arquivo.controller.ArquivoNotaFiscalController;
import br.com.emmanuelneri.app.notafiscal.controller.NotaFiscalController;
import br.com.emmanuelneri.app.notafiscal.repository.NotaFiscalRepository;
import br.com.emmanuelneri.app.notafiscal.service.NotaFiscalService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDiscoveryClient
@ComponentScan(basePackageClasses = {ArquivoNotaFiscalController.class, MongoDBPropertiesConfig.class, NotaFiscalRepository.class, NotaFiscalService.class, NotaFiscalController.class})
@EnableEurekaClient
public class DisponibilizadorAppConfig {

    public static void main(String[] args) {
        SpringApplication.run(DisponibilizadorAppConfig.class, args);
    }
}
