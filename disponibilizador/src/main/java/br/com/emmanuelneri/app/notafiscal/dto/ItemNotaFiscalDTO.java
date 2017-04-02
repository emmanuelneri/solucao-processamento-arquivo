package br.com.emmanuelneri.app.notafiscal.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = {"numeroPedido", "numeroItem"})
public class ItemNotaFiscalDTO {

    private String numeroPedido;
    private Integer numeroItem;
    private BigDecimal quantidade;
    private ProdutoDTO produto;
    private BigDecimal valorProduto;
    private BigDecimal valorTotal;
}
