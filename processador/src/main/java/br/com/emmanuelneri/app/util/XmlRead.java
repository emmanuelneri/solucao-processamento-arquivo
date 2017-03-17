package br.com.emmanuelneri.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.emmanuelneri.app.exception.FileException;
import br.com.emmanuelneri.app.notafiscal.xml.NfeProcXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;

public final class XmlRead {

    private XmlRead() {}

    public static NfeProcXml read(String xml) throws JAXBException, FileException {
        // TODO melhorar leitura de NF para suportar mais tipos de notas
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(NfeProcXml.class);
            final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (NfeProcXml) jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes()));
        } catch (Exception ex) {
            final Logger LOGGER = LoggerFactory.getLogger(XmlRead.class);
            LOGGER.error("Erro no parser do arquivo", ex);
            throw new FileException("Arquivo inválido");
        }
    }

}
