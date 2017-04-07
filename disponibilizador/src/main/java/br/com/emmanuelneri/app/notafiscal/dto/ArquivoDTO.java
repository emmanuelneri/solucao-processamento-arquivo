package br.com.emmanuelneri.app.notafiscal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.bson.Document;

import java.util.Date;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ArquivoDTO {

    public static final String ARQUIVO_ID = "_id";
    private static final String ARQUIVO_NOME = "nomeArquivo";
    private static final String ARQUIVO_DATA = "data";
    private static final String ARQUIVO_CONTEUDO = "conteudo";

    private String id;
    private String nomeArquivo;
    private Date data;
    private String conteudo;

    private ArquivoDTO(String id, String nomeArquivo, Date data) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.data = data;
    }

    public static ArquivoDTO criar(Document document) {
        return new ArquivoDTO(document.getObjectId(ARQUIVO_ID).toHexString(),
                document.getString(ARQUIVO_NOME),
                document.getDate(ARQUIVO_DATA));
    }

    public static ArquivoDTO criarComConteudo(Document document) {
        final ArquivoDTO arquivo = criar(document);
        arquivo.conteudo = document.getString(ARQUIVO_CONTEUDO);
        return arquivo;
    }

}
