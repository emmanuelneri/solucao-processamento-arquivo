package br.com.emmanuelneri.app;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:disponibilizador.properties")
public class DisponibilizadorProperties {

    @Getter
    @Value("${mongo.host}")
    private String mongoHost;

    @Getter
    @Value("${mongo.port}")
    private int mongoPort;

}
