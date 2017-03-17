package br.com.emmanuelneri.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.emmanuelneri.app.model.Empresa;
import br.com.emmanuelneri.app.model.Empresa.TipoEmpresa;
import br.com.emmanuelneri.app.xml.NfeEmpresaXml;
import br.com.emmanuelneri.app.xml.NfeInfoXml;

@Service
public class EmpresaService extends AbstractService {

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
        Empresa empresa = findByCnpj(cnpj);

        if(empresa == null) {
            empresa = new Empresa(cnpj, nome, tipo);
            persist(empresa);
        }
        return empresa;
    }

    private Empresa findByCnpj(String cnpj) {
        return getEntityManager().createNamedQuery("Empresa.findByCnpj",  Empresa.class)
                .setParameter("cnpj", cnpj).getResultList()
                .stream().findFirst().orElse(null);
    }


}
