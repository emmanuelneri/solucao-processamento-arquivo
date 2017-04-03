package br.com.emmanuelneri;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:leitor.properties")
@Getter
public class LeitorProperties {

    @Value("${diretorio.arquivo.novo}")
    private String diretorioNovo;

    @Value("${diretorio.arquivo.bkp}")
    private String diretorioBkp;

    @Value("${diretorio.arquivo.erro}")
    private String diretorioErro;
}
