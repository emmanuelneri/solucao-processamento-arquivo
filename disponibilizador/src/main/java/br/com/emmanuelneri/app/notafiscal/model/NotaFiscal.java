package br.com.emmanuelneri.app.notafiscal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "NotaFiscal.findByCnpjEmitenteENumeroNotaFiscal", query = "select nfe from NotaFiscal nfe join fetch nfe.emitente emit join fetch nfe.destinatario des join fetch nfe.itens where emit.cnpj = ?1 and nfe.numeroNf = ?2"),
        @NamedQuery(name = "NotaFiscal.findAll", query = "select nfe from NotaFiscal nfe join fetch nfe.emitente emit join fetch nfe.destinatario des join fetch nfe.itens order by nfe.dataEmissao desc, emit.nome")
})
@Getter
public class NotaFiscal {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    @Id
    private Long id;

    @Column(name = "numero_nf")
    private String numeroNf;

    @ManyToOne
    @JoinColumn(name = "emitente_id")
    private Empresa emitente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Empresa destinatario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @Column(name = "DATA_EMISSAO")
    private LocalDate dataEmissao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @Column(name = "DATA_SAIDA")
    private LocalDate dataSaida;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTA_FISCAL_ID")
    private List<ItemNotaFiscal> itens = new ArrayList<>();

    private BigDecimal valor;

    @Column(name = "VALOR_DESCONTO")
    private BigDecimal valorDesconto;

    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;
}

