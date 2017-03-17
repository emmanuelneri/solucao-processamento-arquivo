package br.com.emmanuelneri.app.notafiscal.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Nível 4
 */
@XmlRootElement(name = "total")
public class NfeTotalXml {

    private NfeICMSTotXml icmsTotXml;

    @XmlElement(name = "ICMSTot")
    public void setIcmsTotXml(NfeICMSTotXml icmsTotXml) {
        this.icmsTotXml = icmsTotXml;
    }

    public NfeICMSTotXml getIcmsTotXml() {
        return icmsTotXml;
    }

}
