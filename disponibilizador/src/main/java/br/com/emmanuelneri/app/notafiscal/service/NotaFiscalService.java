package br.com.emmanuelneri.app.notafiscal.service;

import br.com.emmanuelneri.app.notafiscal.dto.NotaFiscalDTO;
import br.com.emmanuelneri.app.notafiscal.repository.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    public NotaFiscalDTO findByCnpjEmitenteENumeroNotaFiscal(String cnpjEmitente, String numeroNotaFiscal) {
        return notaFiscalRepository.findByCnpjEmitenteENumeroNotaFiscal(cnpjEmitente, numeroNotaFiscal);
    }

    public List<NotaFiscalDTO> findAll() {
        return notaFiscalRepository.findAll();
    }

}
