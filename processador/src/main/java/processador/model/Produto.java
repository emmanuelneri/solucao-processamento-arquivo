package processador.model;

import processador.util.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"CODIGO", "FORNECEDOR_ID"}, name = "produto_uk"),
        indexes = @Index(columnList = "CODIGO"))
@NamedQueries({
        @NamedQuery(name = "Produto.findByCodigoEFornecedor",
                query = "Select p from Produto p where p.codigo = :codigo and p.fornecedor = :fornecedor")
})
public class Produto implements Model<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String codigo;

    @NotNull
    private String descricao;

    @NotNull
    private String unidade;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FORNECEDOR_ID")
    private Empresa fornecedor;

    protected Produto() {
    }

    public Produto(String codigo, String descricao, Empresa fornecedor, String unidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
        this.unidade = unidade;
    }

    public Long getId() {
        return this.id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Empresa getFornecedor() {
        return fornecedor;
    }

    public String getUnidade() {
        return unidade;
    }

}
