package br.com.emmanuelneri.app.notafiscal.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Nível 4
 */
@XmlRootElement(name = "emit")
public class NfeEmpresaXml {

    private String cnpj;
    private String xNome;
    private String xFant;
    private String ie;
    private String crt;

    @XmlElement(name = "CNPJ")
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @XmlElement
    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    @XmlElement
    public void setxFant(String xFant) {
        this.xFant = xFant;
    }

    @XmlElement(name = "IE")
    public void setIe(String ie) {
        this.ie = ie;
    }

    @XmlElement
    public void setCrt(String crt) {
        this.crt = crt;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getxNome() {
        return xNome;
    }

    public String getxFant() {
        return xFant;
    }

    public String getIe() {
        return ie;
    }

    public String getCrt() {
        return crt;
    }

}
