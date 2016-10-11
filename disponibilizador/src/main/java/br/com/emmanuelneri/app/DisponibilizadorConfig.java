package br.com.emmanuelneri.app;

import br.com.emmanuelneri.disponibilizador.ArquivoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {ArquivoController.class, DisponibilizadorProperties.class})
public class DisponibilizadorConfig {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DisponibilizadorConfig.class, args);
    }
}
