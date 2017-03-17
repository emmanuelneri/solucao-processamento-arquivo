package br.com.emmanuelneri.app.notafiscal.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "CNPJ", name = "empresa_uk"),
        indexes = @Index(columnList = "CNPJ"))
@NamedQueries({
        @NamedQuery(name = "Empresa.findByCnpj", query = "Select e from Empresa e where e.cnpj = ?1 "),
})
@Getter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cnpj;

    @NotNull
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoEmpresa tipoEmpresa;

    protected Empresa() {}

    public Empresa(String cnpj, String nome, TipoEmpresa tipoEmpresa) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.tipoEmpresa = tipoEmpresa;
    }

    public enum TipoEmpresa {
        CONTRATANTE,
        FORNECEDOR;
    }

}
