package br.com.emmanuelneri.app.notafiscal.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Nível 4
 */
@XmlRootElement(name = "cobr")
public class NfeCobrancaXml {

    private NfeFaturamentoXml faturamento;

    private List<NfeDuplicataXml> duplicatas;

    @XmlElement(name = "dup")
    public void setDuplicatas(List<NfeDuplicataXml> duplicatas) {
        this.duplicatas = duplicatas;
    }


    @XmlElement(name = "fat")
    public void setFaturamento(NfeFaturamentoXml faturamento) {
        this.faturamento = faturamento;
    }

    public NfeFaturamentoXml getFaturamento() {
        return faturamento;
    }

    public List<NfeDuplicataXml> getDuplicatas() {
        return duplicatas;
    }
}
