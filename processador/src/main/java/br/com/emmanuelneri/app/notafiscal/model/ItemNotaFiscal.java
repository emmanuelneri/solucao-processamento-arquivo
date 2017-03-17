package br.com.emmanuelneri.app.notafiscal.model;

import lombok.Getter;

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
@Getter
public class ItemNotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMERO_PEDIDO")
    private String numeroPedido;

    @Column(name = "NUMERO_ITEM")
    private Integer numeroItem;

    @Getter
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
}
