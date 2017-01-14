package processador.util;

import processador.xml.NfeProcXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;

public final class XmlRead {

    private XmlRead() {}

    public static NfeProcXml read(String xml) throws JAXBException {
        // TODO melhorar leitura de NF para suportar mais tipos de notas
        final JAXBContext jaxbContext = JAXBContext.newInstance(NfeProcXml.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (NfeProcXml) jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes()));
    }

}
