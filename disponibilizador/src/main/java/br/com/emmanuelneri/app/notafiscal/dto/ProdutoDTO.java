package br.com.emmanuelneri.app.notafiscal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProdutoDTO {

    private String codigo;
    private String descricao;
    private String unidade;

}
