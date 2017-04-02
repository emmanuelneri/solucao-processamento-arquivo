package br.com.emmanuelneri.app.notafiscal.controller;

import br.com.emmanuelneri.app.notafiscal.dto.NotaFiscalDTO;
import br.com.emmanuelneri.app.notafiscal.service.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "nota-fiscal")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService notaFiscalService;

    @RequestMapping(method = RequestMethod.GET, value = "/{cnpjEmitente}/{numeroNotaFiscal}")
    public ResponseEntity findByCnpjEmitenteENumeroNotaFiscal(@PathVariable("cnpjEmitente") String cnpjEmitente, @PathVariable("numeroNotaFiscal") String numeroNotaFiscal) {
        final NotaFiscalDTO notaFiscalDTO = notaFiscalService.findByCnpjEmitenteENumeroNotaFiscal(cnpjEmitente, numeroNotaFiscal);
        return notaFiscalDTO != null ? ResponseEntity.ok(notaFiscalDTO) : ResponseEntity.badRequest().body("Nota fiscal não encontrada");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll() {
        return ResponseEntity.ok(notaFiscalService.findAll());
    }


}
