package processador.model;

import processador.util.Model;

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
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "CNPJ", name = "empresa_uk"),
        indexes = @Index(columnList = "CNPJ"))
@NamedQueries({
        @NamedQuery(name = "Empresa.findByCnpj",
                query = "Select e from Empresa e where e.cnpj = :cnpj "),
})
public class Empresa implements Model<Long> {

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

    @Override
    public Long getId() {
        return this.id;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getNome() {
        return this.nome;
    }

    public TipoEmpresa getTipoEmpresa() {
        return tipoEmpresa;
    }


    public enum TipoEmpresa {
        CONTRATANTE,
        FORNECEDOR;
    }

}
