package br.com.emmanuelneri.app.notafiscal.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "cnpj")
public class EmpresaDTO {

    private String cnpj;
    private String nome;
}
