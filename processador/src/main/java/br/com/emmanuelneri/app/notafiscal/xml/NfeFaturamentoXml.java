package br.com.emmanuelneri.app.notafiscal.xml;

import br.com.emmanuelneri.app.util.converter.BigDecimalAdapterXml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

/**
 * Nível 5
 */
@XmlRootElement(name = "fat")
public class NfeFaturamentoXml {

    private String nFat;
    private BigDecimal vOrig;
    private BigDecimal vLiq;

    @XmlElement
    public void setnFat(String nFat) {
        this.nFat = nFat;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvOrig(BigDecimal vOrig) {
        this.vOrig = vOrig;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvLiq(BigDecimal vLiq) {
        this.vLiq = vLiq;
    }

    public String getnFat() {
        return nFat;
    }

    public BigDecimal getvOrig() {
        return vOrig;
    }

    public BigDecimal getvLiq() {
        return vLiq;
    }

}
