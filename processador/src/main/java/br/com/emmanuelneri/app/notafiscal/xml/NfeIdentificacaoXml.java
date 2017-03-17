package br.com.emmanuelneri.app.notafiscal.xml;

import br.com.emmanuelneri.app.util.converter.LocalDateAdapterXml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Nível 4
 */
@XmlRootElement(name = "ide")
public class NfeIdentificacaoXml {

    private String cUF;
    private String cNF;
    private String natOp;
    private String indPag;
    private String mod;
    private String serie;
    private String nNF;
    private LocalDate dEmi;
    private LocalDate dSaiEnt;
    private String tpNF;
    private String cMunFG;
    private String tpImp;
    private String tpEmis;
    private String cDV;
    private String tpAmb;
    private String finNFe;
    private String procEmi;
    private String verProc;

    @XmlElement
    public void setcUF(String cUF) {
        this.cUF = cUF;
    }

    @XmlElement
    public void setcNF(String cNF) {
        this.cNF = cNF;
    }

    @XmlElement
    public void setNatOp(String natOp) {
        this.natOp = natOp;
    }

    @XmlElement
    public void setIndPag(String indPag) {
        this.indPag = indPag;
    }

    @XmlElement
    public void setMod(String mod) {
        this.mod = mod;
    }

    @XmlElement
    public void setSerie(String serie) {
        this.serie = serie;
    }

    @XmlElement
    public void setnNF(String nNF) {
        this.nNF = nNF;
    }

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapterXml.class)
    public void setdEmi(LocalDate dEmi) {
        this.dEmi = dEmi;
    }

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapterXml.class)
    public void setdSaiEnt(LocalDate dSaiEnt) {
        this.dSaiEnt = dSaiEnt;
    }

    @XmlElement
    public void setTpNF(String tpNF) {
        this.tpNF = tpNF;
    }

    @XmlElement
    public void setcMunFG(String cMunFG) {
        this.cMunFG = cMunFG;
    }

    @XmlElement
    public void setTpImp(String tpImp) {
        this.tpImp = tpImp;
    }

    @XmlElement
    public void setTpEmis(String tpEmis) {
        this.tpEmis = tpEmis;
    }

    @XmlElement
    public void setcDV(String cDV) {
        this.cDV = cDV;
    }

    @XmlElement
    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    @XmlElement
    public void setFinNFe(String finNFe) {
        this.finNFe = finNFe;
    }

    @XmlElement
    public void setProcEmi(String procEmi) {
        this.procEmi = procEmi;
    }

    @XmlElement
    public void setVerProc(String verProc) {
        this.verProc = verProc;
    }

    public String getcUF() {
        return cUF;
    }

    public String getcNF() {
        return cNF;
    }

    public String getNatOp() {
        return natOp;
    }

    public String getIndPag() {
        return indPag;
    }

    public String getMod() {
        return mod;
    }

    public String getSerie() {
        return serie;
    }

    public String getnNF() {
        return nNF;
    }

    public LocalDate getdEmi() {
        return dEmi;
    }

    public LocalDate getdSaiEnt() {
        return dSaiEnt;
    }

    public String getTpNF() {
        return tpNF;
    }

    public String getcMunFG() {
        return cMunFG;
    }

    public String getTpImp() {
        return tpImp;
    }

    public String getTpEmis() {
        return tpEmis;
    }

    public String getcDV() {
        return cDV;
    }

    public String getTpAmb() {
        return tpAmb;
    }

    public String getFinNFe() {
        return finNFe;
    }

    public String getProcEmi() {
        return procEmi;
    }

    public String getVerProc() {
        return verProc;
    }

}
