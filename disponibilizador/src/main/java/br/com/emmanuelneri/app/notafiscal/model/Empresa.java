package br.com.emmanuelneri.app.notafiscal.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Getter
public class Empresa {

    @Id
    private Long id;
    private String cnpj;
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoEmpresa tipoEmpresa;

    public enum TipoEmpresa {
        CONTRATANTE,
        FORNECEDOR;
    }

}
