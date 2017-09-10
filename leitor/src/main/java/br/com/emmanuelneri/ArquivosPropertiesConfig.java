package br.com.emmanuelneri;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Getter
public class ArquivosPropertiesConfig {

    @Value("${diretorio.arquivo}")
    private String diretorio;
}
