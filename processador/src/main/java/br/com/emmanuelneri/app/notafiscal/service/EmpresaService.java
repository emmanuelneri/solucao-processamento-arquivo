package br.com.emmanuelneri.app.notafiscal.service;

import br.com.emmanuelneri.app.notafiscal.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.emmanuelneri.app.notafiscal.model.Empresa;
import br.com.emmanuelneri.app.notafiscal.model.Empresa.TipoEmpresa;
import br.com.emmanuelneri.app.notafiscal.xml.NfeEmpresaXml;
import br.com.emmanuelneri.app.notafiscal.xml.NfeInfoXml;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public Empresa buscaEmitenteSenaoCria(NfeInfoXml info) {
        final NfeEmpresaXml emitente = info.getEmitente();
        return buscaEmitenteSenaoCria(emitente.getCnpj(), emitente.getxNome(), TipoEmpresa.FORNECEDOR);
    }

    @Transactional
    public Empresa buscaDestinatarioSenaoCria(NfeInfoXml info) {
        final NfeEmpresaXml destinatario = info.getDestinatario();
        return buscaEmitenteSenaoCria(destinatario.getCnpj(), destinatario.getxNome(), TipoEmpresa.CONTRATANTE);
    }

    private Empresa buscaEmitenteSenaoCria(String cnpj, String nome, TipoEmpresa tipo) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj);

        if(empresa == null) {
            empresa = new Empresa(cnpj, nome, tipo);
            empresaRepository.save(empresa);
        }
        return empresa;
    }
}
