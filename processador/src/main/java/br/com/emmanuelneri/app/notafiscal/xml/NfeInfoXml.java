package br.com.emmanuelneri.app.notafiscal.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Nível 3
 */
@XmlRootElement(name = "infNFe")
public class NfeInfoXml {

    private NfeIdentificacaoXml identificacao;

    private NfeEmpresaXml emitente;

    private NfeEmpresaXml destinatario;

    private List<NfeDetXml> dets;

    private NfeTotalXml total;

    private NfeCobrancaXml cobranca;

    @XmlElement(name = "ide")
    public void setIdentificacao(NfeIdentificacaoXml identificacao) {
        this.identificacao = identificacao;
    }

    @XmlElement(name = "emit")
    public void setEmitente(NfeEmpresaXml emitente) {
        this.emitente = emitente;
    }

    @XmlElement(name = "det")
    public void setDets(List<NfeDetXml> dets) {
        this.dets = dets;
    }

    @XmlElement(name = "total")
    public void setTotal(NfeTotalXml total) {
        this.total = total;
    }

    @XmlElement(name = "cobr")
    public void setCobranca(NfeCobrancaXml cobranca) {
        this.cobranca = cobranca;
    }

    @XmlElement(name = "dest")
    public void setDestinatario(NfeEmpresaXml destinatario) {
        this.destinatario = destinatario;
    }

    public NfeIdentificacaoXml getIdentificacao() {
        return identificacao;
    }

    public NfeEmpresaXml getEmitente() {
        return emitente;
    }

    public NfeEmpresaXml getDestinatario() {
        return destinatario;
    }

    public List<NfeDetXml> getDets() {
        return dets;
    }

    public NfeTotalXml getTotal() {
        return total;
    }

    public NfeCobrancaXml getCobranca() {
        return cobranca;
    }

    public NfeICMSTotXml getIcmsTotXml() {
        return total.getIcmsTotXml();
    }
}
