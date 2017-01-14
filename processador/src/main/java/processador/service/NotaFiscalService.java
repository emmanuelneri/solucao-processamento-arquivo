package processador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import processador.exception.BusinessException;
import processador.model.Empresa;
import processador.model.NotaFiscal;
import processador.model.Produto;
import processador.util.XmlRead;
import processador.xml.NfeDetXml;
import processador.xml.NfeICMSTotXml;
import processador.xml.NfeIdentificacaoXml;
import processador.xml.NfeInfoXml;
import processador.xml.NfeProcXml;
import processador.xml.NfeProdutoXml;

import java.util.Map;

@Service
public class NotaFiscalService extends AbstractService{

    private Logger LOGGER = LoggerFactory.getLogger(NotaFiscalService.class);

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ProdutoService produtoService;

    @Transactional
    public void processar(String arquivoNotaFiscal) {
        try {
            final NfeProcXml xmlNotaFiscal = XmlRead.read(arquivoNotaFiscal);
            salvar(xmlNotaFiscal);
        } catch (BusinessException bex) {
            LOGGER.warn("Nota Fiscal já existente", bex);
        } catch (Exception ex) {
            LOGGER.error("Erro processar notaFisca", ex);
        }
    }

    private void salvar(NfeProcXml xml) throws BusinessException {
        final NfeInfoXml info = xml.getInfo();
        final NfeIdentificacaoXml identificacao = info.getIdentificacao();
        final NfeICMSTotXml total = info.getIcmsTotXml();

        final Empresa emitente = empresaService.buscaEmitenteSenaoCria(info);
        final Empresa destinatario = empresaService.buscaDestinatarioSenaoCria(info);

        validaNotaExistente(identificacao.getnNF(), emitente);

        final NotaFiscal notaFiscal = new NotaFiscal(identificacao, total, emitente, destinatario);

        final Map<String, Produto> produtosExistentesPorCodigo = produtoService.findProdutoPorCodigoByfornecedor(info.getDets(), emitente);

        for(NfeDetXml det : info.getDets()) {
            for(NfeProdutoXml produtoXml : det.getProdutos()) {
                final Produto produto = produtoService.getProdutoSenaoCria(produtosExistentesPorCodigo, produtoXml, emitente);
                notaFiscal.addItem(produtoXml, produto);
            }
        }

        persist(notaFiscal);
    }

    private void validaNotaExistente(String numeroNf, Empresa emitente) throws BusinessException {
        final NotaFiscal notaFiscal = findByNumeroEmitente(numeroNf, emitente);
        if(notaFiscal != null) {
            throw new BusinessException(String.format("O Número %s de Nota fiscal já existente para o emitente %s (%s)",
                    numeroNf, emitente.getNome(), emitente.getCnpj()));
        }
    }

    private NotaFiscal findByNumeroEmitente(String numeroNf, Empresa emitente) {
        return getEntityManager().createNamedQuery("Nfe.findByEmitenteENumero", NotaFiscal.class)
                .setParameter("numeroNf", numeroNf)
                .setParameter("idEmpresaEmitente", emitente.getId())
                .getResultList().stream().findFirst().orElse(null);
    }

}
