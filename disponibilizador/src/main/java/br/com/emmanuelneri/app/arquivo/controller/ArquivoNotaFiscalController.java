package br.com.emmanuelneri.app.arquivo.controller;

import br.com.emmanuelneri.app.arquivo.service.NotaFiscalArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/arquivos")
public class ArquivoNotaFiscalController {

    @Autowired
    private NotaFiscalArquivoService notaFiscalArquivoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll() {
        return ResponseEntity.ok(notaFiscalArquivoService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(notaFiscalArquivoService.findById(id));
    }

}
