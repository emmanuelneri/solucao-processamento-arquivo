package br.com.emmanuelneri.app.notafiscal.xml;

import br.com.emmanuelneri.app.util.converter.BigDecimalAdapterXml;
import br.com.emmanuelneri.app.util.converter.IntegerAdapterXml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

/**
 * Nível 5
 */
@XmlRootElement(name = "prod")
public class NfeProdutoXml {

    private String cProd;
    private String cEAN ;
    private String xProd;
    private String ncm;
    private String cfop;
    private String uCom;
    private BigDecimal qCom;
    private BigDecimal vUnCom;
    private BigDecimal vProd;
    private String cEANTrib;
    private String uTrib;
    private BigDecimal qTrib;
    private String vUnTrib;
    private Integer indTot;
    private String xPed;
    private Integer nItemPed;

    @XmlElement
    public void setcProd(String cProd) {
        this.cProd = cProd;
    }

    @XmlElement
    public void setcEAN(String cEAN) {
        this.cEAN = cEAN;
    }

    @XmlElement
    public void setxProd(String xProd) {
        this.xProd = xProd;
    }

    @XmlElement
    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    @XmlElement
    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    @XmlElement
    public void setuCom(String uCom) {
        this.uCom = uCom;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setqCom(BigDecimal qCom) {
        this.qCom = qCom;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvUnCom(BigDecimal vUnCom) {
        this.vUnCom = vUnCom;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvProd(BigDecimal vProd) {
        this.vProd = vProd;
    }

    @XmlElement
    public void setcEANTrib(String cEANTrib) {
        this.cEANTrib = cEANTrib;
    }

    @XmlElement
    public void setuTrib(String uTrib) {
        this.uTrib = uTrib;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setqTrib(BigDecimal qTrib) {
        this.qTrib = qTrib;
    }

    @XmlElement
    public void setvUnTrib(String vUnTrib) {
        this.vUnTrib = vUnTrib;
    }

    @XmlElement
    @XmlJavaTypeAdapter(IntegerAdapterXml.class)
    public void setIndTot(Integer indTot) {
        this.indTot = indTot;
    }

    @XmlElement
    public void setxPed(String xPed) {
        this.xPed = xPed;
    }

    @XmlElement
    @XmlJavaTypeAdapter(IntegerAdapterXml.class)
    public void setnItemPed(Integer nItemPed) {
        this.nItemPed = nItemPed;
    }

    public String getcProd() {
        return cProd;
    }

    public String getcEAN() {
        return cEAN;
    }

    public String getxProd() {
        return xProd;
    }

    public String getNcm() {
        return ncm;
    }

    public String getCfop() {
        return cfop;
    }

    public String getuCom() {
        return uCom;
    }

    public BigDecimal getqCom() {
        return qCom;
    }

    public BigDecimal getvUnCom() {
        return vUnCom;
    }

    public BigDecimal getvProd() {
        return vProd;
    }

    public String getcEANTrib() {
        return cEANTrib;
    }

    public String getuTrib() {
        return uTrib;
    }

    public BigDecimal getqTrib() {
        return qTrib;
    }

    public String getvUnTrib() {
        return vUnTrib;
    }

    public Integer getIndTot() {
        return indTot;
    }

    public String getxPed() {
        return xPed;
    }

    public Integer getnItemPed() {
        return nItemPed;
    }
}
