package br.com.emmanuelneri;

import br.com.emmanuelneri.app.ArquivoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {ArquivoController.class, DisponibilizadorProperties.class})
public class DisponibilizadorAppConfig {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DisponibilizadorAppConfig.class, args);
    }
}
