package br.com.emmanuelneri.app.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "notaFiscalXml")
@Getter
public class NotaFiscalXml {

    @Id
    public String id;
    public Date data;
    private String nomeArquivo;
    private String conteudo;

    public NotaFiscalXml(String nomeArquivo, String conteudo) {
        this.nomeArquivo = nomeArquivo;
        this.conteudo = conteudo;
        this.data = new Date();
    }
}
