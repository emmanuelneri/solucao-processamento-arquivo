package br.com.emmanuelneri.app.notafiscal.model;

import br.com.emmanuelneri.app.notafiscal.xml.NfeICMSTotXml;
import br.com.emmanuelneri.app.notafiscal.xml.NfeIdentificacaoXml;
import br.com.emmanuelneri.app.notafiscal.xml.NfeProdutoXml;
import lombok.Getter;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NOTA_FISCAL",
        uniqueConstraints = @UniqueConstraint(columnNames = {"numero_nf", "emitente_id"}, name = "nota_fiscal_uk"),
        indexes = {@Index(columnList = "NUMERO_NF", name = "nfe_numero_nf_idx")})
@NamedQueries(value = {
        @NamedQuery(name = "NotaFiscal.findByEmitenteENumero", query = "select nfe from NotaFiscal nfe join fetch nfe.emitente emit join fetch nfe.destinatario des join fetch nfe.itens where nfe.numeroNf = ?1 and emit.id = ?2")
})
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotNull
    @Column(name = "numero_nf")
    @Getter
    private String numeroNf;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "emitente_id")
    @Getter
    private Empresa emitente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    @Getter
    private Empresa destinatario;

    @NotNull
    @Column(name = "DATA_EMISSAO")
    @Getter
    private LocalDate dataEmissao;

    @Column(name = "DATA_SAIDA")
    @Getter
    private LocalDate dataSaida;

    @NotNull
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "notaFiscal")
    private List<ItemNotaFiscal> itens = new ArrayList<>();

    @NotNull
    @Getter
    private BigDecimal valor;

    @Column(name = "VALOR_DESCONTO")
    @Getter
    private BigDecimal valorDesconto;

    @NotNull
    @Column(name = "VALOR_TOTAL")
    @Getter
    private BigDecimal valorTotal;

    @NotNull
    @Column(name = "DATA_HORA_PROCESSAMENTO")
    @Getter
    private LocalDateTime dataProcessamento = LocalDateTime.now();

    protected NotaFiscal() {
    }

    public NotaFiscal(NfeIdentificacaoXml identificacao, NfeICMSTotXml total, Empresa destinatario, Empresa emitente) {
        this.numeroNf = identificacao.getnNF();
        this.destinatario = destinatario;
        this.emitente = emitente;
        this.dataEmissao = identificacao.getdEmi();
        this.dataSaida = identificacao.getdSaiEnt();
        this.valor = total.getvProd();
        this.valorDesconto = total.getvDesc();
        this.valorTotal = total.getvNF();
    }

    public void addItem(NfeProdutoXml produtoXml, Produto produto) {
        itens.add(new ItemNotaFiscal(this, produtoXml.getxPed(), produtoXml.getnItemPed(),
                produtoXml.getqTrib(), produto, produtoXml.getvUnCom(), produtoXml.getvProd()));
    }
}

