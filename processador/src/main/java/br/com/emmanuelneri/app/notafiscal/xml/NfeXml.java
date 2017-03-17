package br.com.emmanuelneri.app.notafiscal.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Nível 2
 */
@XmlRootElement(name = "NFe")
public class NfeXml {

    private NfeInfoXml info;

    @XmlElement(name = "infNFe")
    public void setInfo(NfeInfoXml info) {
        this.info = info;
    }

    public NfeInfoXml getInfo() {
        return info;
    }

}
