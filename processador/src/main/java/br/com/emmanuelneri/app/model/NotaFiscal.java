package br.com.emmanuelneri.app.model;

import br.com.emmanuelneri.app.util.Model;
import br.com.emmanuelneri.app.xml.NfeICMSTotXml;
import br.com.emmanuelneri.app.xml.NfeIdentificacaoXml;
import br.com.emmanuelneri.app.xml.NfeProdutoXml;

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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NOTA_FISCAL",
        uniqueConstraints = @UniqueConstraint(columnNames = {"numero_nf", "emitente_id"}, name = "nota_fiscal_uk"),
        indexes = {@Index(columnList = "NUMERO_NF", name = "nfe_numero_nf_idx")})
@NamedQueries(value = {
        @NamedQuery(name = "Nfe.findByEmitenteENumero", query = "select nfe from NotaFiscal nfe join fetch nfe.emitente emit join fetch nfe.destinatario des join fetch nfe.itens where nfe.numeroNf = :numeroNf and emit.id = :idEmpresaEmitente")
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
    @JoinColumn(name = "NOTA_FISCAL_ID")
    private List<ItemNotaFiscal> itens = new ArrayList<>();

    @NotNull
    private BigDecimal valor;

    @Column(name = "VALOR_DESCONTO")
    private BigDecimal valorDesconto;

    @NotNull
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    protected NotaFiscal() {}

    public NotaFiscal(NfeIdentificacaoXml identificacao, NfeICMSTotXml total, Empresa destinatario, Empresa emitente) {
        this.numeroNf = identificacao.getnNF();
        this.destinatario = destinatario;
        this.emitente = emitente ;
        this.dataEmissao = identificacao.getdEmi();
        this.dataSaida = identificacao.getdSaiEnt();
        this.valor = total.getvProd();
        this.valorDesconto = total.getvDesc() ;
        this.valorTotal = total.getvNF();
    }

    public void addItem(NfeProdutoXml produtoXml, Produto produto) {
        itens.add(new ItemNotaFiscal(produtoXml.getxPed(), produtoXml.getnItemPed(),
                produtoXml.getqTrib(), produto, produtoXml.getvUnCom(), produtoXml.getvProd()));
    }

    @Override
    public Long getId() {
        return id;
    }
}
