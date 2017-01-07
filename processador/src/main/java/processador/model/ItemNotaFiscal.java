package processador.model;

import processador.util.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "ITEM_NOTA_FISCAL",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NUMERO_PEDIDO", "NUMERO_ITEM"}, name = "nfe_item_uk"))
public class ItemNotaFiscal implements Model<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMERO_PEDIDO")
    private String numeroPedido;

    @Column(name = "NUMERO_ITEM")
    private Integer numeroItem;

    private BigDecimal quantidade;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotNull
    @Column(name = "VALOR_PRODUTO")
    private BigDecimal valorProduto;

    @NotNull
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    protected ItemNotaFiscal() {
    }

    public ItemNotaFiscal(String numeroPedido, Integer numeroItem, BigDecimal quantidade, Produto produto, BigDecimal valorProduto, BigDecimal valorTotal) {
        this.numeroPedido = numeroPedido;
        this.numeroItem = numeroItem;
        this.quantidade = quantidade;
        this.produto = produto;
        this.valorProduto = valorProduto;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return this.id;
    }

    public Integer getNumeroItem() {
        return numeroItem;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public String getNumeroItemLabel() {
        return numeroItem == null || numeroItem.equals(0) ? "Não informado" : String.valueOf(numeroItem);
    }
}
