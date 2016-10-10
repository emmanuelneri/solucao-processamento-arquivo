package br.com.emmanuelneri.app;

import br.com.emmanuelneri.leitor.LeitorArquivoScheduled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackageClasses = {LeitorArquivoScheduled.class, LeitorProperties.class})
public class LeitorConfig {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(LeitorConfig.class, args);
    }
}
