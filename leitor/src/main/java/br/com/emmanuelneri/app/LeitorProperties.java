package br.com.emmanuelneri.app;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:leitor.properties")
public class LeitorProperties {

    @Getter
    @Value("${diretorio.arquivo.novo}")
    private String diretorioNovo;

    @Getter
    @Value("${diretorio.arquivo.bkp}")
    private String diretorioBkp;

}
