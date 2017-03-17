package br.com.emmanuelneri.app.notafiscal.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Nível 1
 */
@XmlRootElement(name = "nfeProc", namespace = "http://www.portalfiscal.inf.br/nfe")
public class NfeProcXml {

    private NfeXml nfe;

    @XmlElement(name = "NFe")
    public void setNfe(NfeXml nfe) {
        this.nfe = nfe;
    }

    public NfeXml getNfe() {
        return nfe;
    }

    public NfeInfoXml getInfo() {
        return nfe.getInfo();
    }

}
