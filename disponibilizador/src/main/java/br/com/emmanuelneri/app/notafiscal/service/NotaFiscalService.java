package br.com.emmanuelneri.app.notafiscal.service;

import br.com.emmanuelneri.app.notafiscal.model.NotaFiscal;
import br.com.emmanuelneri.app.notafiscal.repository.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    public NotaFiscal findByCnpjEmitenteENumeroNotaFiscal(String cnpjEmitente, String numeroNotaFiscal) {
        return notaFiscalRepository.findByCnpjEmitenteENumeroNotaFiscal(cnpjEmitente, numeroNotaFiscal);
    }

    public List<NotaFiscal> findAll() {
        return notaFiscalRepository.findAll();
    }

}
