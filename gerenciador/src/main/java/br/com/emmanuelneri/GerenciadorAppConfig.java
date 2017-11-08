package br.com.emmanuelneri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GerenciadorAppConfig {

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorAppConfig.class, args);
    }
}
