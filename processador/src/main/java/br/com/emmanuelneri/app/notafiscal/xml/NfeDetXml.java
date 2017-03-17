package br.com.emmanuelneri.app.notafiscal.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Nível 4
 */
@XmlRootElement(name = "det")
public class NfeDetXml {

    private List<NfeProdutoXml> produtos;

    @XmlElement(name = "prod")
    public void setProdutos(List<NfeProdutoXml> produtos) {
        this.produtos = produtos;
    }

    public List<NfeProdutoXml> getProdutos() {
        return produtos;
    }
}
