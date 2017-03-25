package br.com.emmanuelneri.app.notafiscal.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
public class ItemNotaFiscal {

    @Id
    private Long id;

    @Column(name = "NUMERO_PEDIDO")
    private String numeroPedido;

    @Column(name = "NUMERO_ITEM")
    private Integer numeroItem;

    private BigDecimal quantidade;

    @ManyToOne
    private Produto produto;

    @Column(name = "VALOR_PRODUTO")
    private BigDecimal valorProduto;

    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;
}
