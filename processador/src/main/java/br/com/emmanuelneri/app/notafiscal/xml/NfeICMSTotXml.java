package br.com.emmanuelneri.app.notafiscal.xml;

import br.com.emmanuelneri.app.util.converter.BigDecimalAdapterXml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

/**
 * Nível 5
 */
@XmlRootElement(name = "ICMSTot")
public class NfeICMSTotXml {

    private BigDecimal vBC;
    private BigDecimal vICMS;
    private BigDecimal vBCST;
    private BigDecimal vST;
    private BigDecimal vProd;
    private BigDecimal vFrete;
    private BigDecimal vSeg;
    private BigDecimal vDesc;
    private BigDecimal vII;
    private BigDecimal vIPI;
    private BigDecimal vPIS;
    private BigDecimal vCOFINS;
    private BigDecimal vOutro;
    private BigDecimal vNF;

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvBC(BigDecimal vBC) {
        this.vBC = vBC;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvICMS(BigDecimal vICMS) {
        this.vICMS = vICMS;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvBCST(BigDecimal vBCST) {
        this.vBCST = vBCST;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvST(BigDecimal vST) {
        this.vST = vST;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvProd(BigDecimal vProd) {
        this.vProd = vProd;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvFrete(BigDecimal vFrete) {
        this.vFrete = vFrete;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvSeg(BigDecimal vSeg) {
        this.vSeg = vSeg;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvDesc(BigDecimal vDesc) {
        this.vDesc = vDesc;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvII(BigDecimal vII) {
        this.vII = vII;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvIPI(BigDecimal vIPI) {
        this.vIPI = vIPI;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvPIS(BigDecimal vPIS) {
        this.vPIS = vPIS;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvCOFINS(BigDecimal vCOFINS) {
        this.vCOFINS = vCOFINS;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvOutro(BigDecimal vOutro) {
        this.vOutro = vOutro;
    }

    @XmlElement
    @XmlJavaTypeAdapter(BigDecimalAdapterXml.class)
    public void setvNF(BigDecimal vNF) {
        this.vNF = vNF;
    }

    public BigDecimal getvBC() {
        return vBC;
    }

    public BigDecimal getvICMS() {
        return vICMS;
    }

    public BigDecimal getvBCST() {
        return vBCST;
    }

    public BigDecimal getvST() {
        return vST;
    }

    public BigDecimal getvProd() {
        return vProd;
    }

    public BigDecimal getvFrete() {
        return vFrete;
    }

    public BigDecimal getvSeg() {
        return vSeg;
    }

    public BigDecimal getvDesc() {
        return vDesc;
    }

    public BigDecimal getvII() {
        return vII;
    }

    public BigDecimal getvIPI() {
        return vIPI;
    }

    public BigDecimal getvPIS() {
        return vPIS;
    }

    public BigDecimal getvCOFINS() {
        return vCOFINS;
    }

    public BigDecimal getvOutro() {
        return vOutro;
    }

    public BigDecimal getvNF() {
        return vNF;
    }

}
