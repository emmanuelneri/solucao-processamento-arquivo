package br.com.emmanuelneri.app.service;

import br.com.emmanuelneri.app.model.NotaFiscalXml;
import br.com.emmanuelneri.app.repository.NotaFiscalXmlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalXmlService {

    @Autowired
    private NotaFiscalXmlRepository notaFiscalXmlRepository;

    public void salvar(String nomeArquivo, String xml) {
        notaFiscalXmlRepository.save(new NotaFiscalXml(nomeArquivo, xml));
    }

}
