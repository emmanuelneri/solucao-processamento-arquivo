package processador.model;

import processador.util.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import java.util.List;

@Entity
@Table(name = "NOTA_FISCAL",
        uniqueConstraints = @UniqueConstraint(columnNames = {"numero_nf", "emitente_id"}, name = "nota_fiscal_uk"),
        indexes = {@Index(columnList = "NUMERO_NF", name = "nfe_numero_nf_idx")})
@NamedQueries(value = {
        @NamedQuery(name = "Nfe.findByEmitenteENumero", query = "select nfe from NotaFiscal nfe join nfe.emitente emit join fetch nfe.itens where nfe.numeroNf = :numeroNf and emit.id = :emitenteId")
})
public class NotaFiscal implements Model<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numero_nf")
    private String numeroNf;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "emitente_id")
    private Empresa emitente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Empresa destinatario;

    @NotNull
    @Column(name = "DATA_UPLOAD")
    private LocalDate dataUpload = LocalDate.now();

    @NotNull
    @Column(name = "DATA_EMISSAO")
    private LocalDate dataEmissao;

    @Column(name = "DATA_SAIDA")
    private LocalDate dataSaida;

    @NotNull
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "NFE_ID")
    private List<ItemNotaFiscal> itens;

    @NotNull
    private BigDecimal valor;

    @Column(name = "VALOR_DESCONTO")
    private BigDecimal valorDesconto;

    @NotNull
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    public Long getId() {
        return id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public Empresa getDestinatario() {
        return destinatario;
    }

    public Empresa getEmitente() {
        return emitente;
    }

    public List<ItemNotaFiscal> getItens() {
        return itens;
    }

    public String getNumeroNf() {
        return numeroNf;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setEmitente(Empresa emitente) {
        this.emitente = emitente;
    }

    public void setDestinatario(Empresa destinatario) {
        this.destinatario = destinatario;
    }

    public void setItens(List<ItemNotaFiscal> itens) {
        this.itens = itens;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setNumeroNf(String numeroNf) {
        this.numeroNf = numeroNf;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDate getDataUpload() {
        return dataUpload;
    }

}
