package processador.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import processador.model.Empresa;
import processador.model.Empresa.TipoEmpresa;
import processador.xml.NfeEmpresaXml;
import processador.xml.NfeInfoXml;

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
