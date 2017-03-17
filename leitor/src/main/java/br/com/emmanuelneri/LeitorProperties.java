package br.com.emmanuelneri;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:leitor.properties")
public class LeitorProperties {

    @Getter
    @Value("${mongo.host}")
    private String mongoHost;

    @Getter
    @Value("${mongo.port}")
    private int mongoPort;

    @Getter
    @Value("${diretorio.arquivo.novo}")
    private String diretorioNovo;

    @Getter
    @Value("${diretorio.arquivo.bkp}")
    private String diretorioBkp;

}
