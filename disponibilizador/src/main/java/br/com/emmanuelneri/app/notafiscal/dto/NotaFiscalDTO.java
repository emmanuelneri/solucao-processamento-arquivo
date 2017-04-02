package br.com.emmanuelneri.app.notafiscal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = {"numeroNf", "emitente"})
public class NotaFiscalDTO {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private String numeroNf;
    private EmpresaDTO emitente;
    private EmpresaDTO destinatario;
    private Set<ItemNotaFiscalDTO> itens = new HashSet<>();
    private BigDecimal valor;
    private BigDecimal valorDesconto;
    private BigDecimal valorTotal;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate dataEmissao;

    public NotaFiscalDTO(String numeroNf, BigDecimal valor, BigDecimal valorDesconto, BigDecimal valorTotal, LocalDate dataEmissao) {
        this.numeroNf = numeroNf;
        this.valor = valor;
        this.valorDesconto = valorDesconto;
        this.valorTotal = valorTotal;
        this.dataEmissao = dataEmissao;
    }

    public void relacionarEmitente(EmpresaDTO emitente) {
        this.emitente = emitente;
    }

    public void relacionarDestinatario(EmpresaDTO destinatario) {
        this.destinatario = destinatario;
    }

    public void adicionarItem(ItemNotaFiscalDTO item) {
        this.itens.add(item);
    }

}

