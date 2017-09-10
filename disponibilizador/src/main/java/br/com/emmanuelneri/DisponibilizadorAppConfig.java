package br.com.emmanuelneri;

import br.com.emmanuelneri.app.arquivo.controller.ArquivoNotaFiscalController;
import br.com.emmanuelneri.app.notafiscal.controller.NotaFiscalController;
import br.com.emmanuelneri.app.notafiscal.repository.NotaFiscalRepository;
import br.com.emmanuelneri.app.notafiscal.service.NotaFiscalService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@ComponentScan(basePackageClasses = {ArquivoNotaFiscalController.class, MongoDBPropertiesConfig.class, NotaFiscalRepository.class, NotaFiscalService.class, NotaFiscalController.class})
public class DisponibilizadorAppConfig {

    public static void main(String[] args) {
        SpringApplication.run(DisponibilizadorAppConfig.class, args);
    }
}
