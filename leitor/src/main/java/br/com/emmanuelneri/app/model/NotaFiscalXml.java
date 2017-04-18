package br.com.emmanuelneri.app.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Document(collection = "notaFiscalXml")
@Getter
public class NotaFiscalXml {

    @Id
    private String id;
    private String data;
    private String nomeArquivo;
    private String conteudo;

    public NotaFiscalXml(String nomeArquivo, String conteudo) {
        this.nomeArquivo = nomeArquivo;
        this.conteudo = conteudo;
        this.data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
