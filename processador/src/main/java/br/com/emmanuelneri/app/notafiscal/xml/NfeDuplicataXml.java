package br.com.emmanuelneri.app.notafiscal.xml;


import br.com.emmanuelneri.app.util.converter.BigDecimalAdapterXml;
import br.com.emmanuelneri.app.util.converter.LocalDateAdapterXml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Nível 5
 */
@XmlRootElement(name = "dub")
public class NfeDuplicataXml {

    private String nDup;
    private LocalDate dVenc;
    private BigDecimal vDup;

    @XmlElement
    public void setnDup(String nDup) {
        this.nDup = nDup;
    }

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapterXml.class)
    public void setdVenc(LocalDate dVenc) {
        this.dVenc = dVenc;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvDup(BigDecimal vDup) {
        this.vDup = vDup;
    }

    public String getnDup() {
        return nDup;
    }

    public LocalDate getdVenc() {
        return dVenc;
    }

    public BigDecimal getvDup() {
        return vDup;
    }

}
