package br.com.emmanuelneri.app.notafiscal.service;

import br.com.emmanuelneri.app.notafiscal.repository.NotaFiscalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.emmanuelneri.app.ArquivoNotaFiscalSender;
import br.com.emmanuelneri.app.exception.BusinessException;
import br.com.emmanuelneri.app.exception.FileException;
import br.com.emmanuelneri.app.notafiscal.model.Empresa;
import br.com.emmanuelneri.app.notafiscal.model.NotaFiscal;
import br.com.emmanuelneri.app.notafiscal.model.Produto;
import br.com.emmanuelneri.app.util.XmlRead;
import br.com.emmanuelneri.app.notafiscal.xml.NfeDetXml;
import br.com.emmanuelneri.app.notafiscal.xml.NfeICMSTotXml;
import br.com.emmanuelneri.app.notafiscal.xml.NfeIdentificacaoXml;
import br.com.emmanuelneri.app.notafiscal.xml.NfeInfoXml;
import br.com.emmanuelneri.app.notafiscal.xml.NfeProcXml;
import br.com.emmanuelneri.app.notafiscal.xml.NfeProdutoXml;

import java.util.Map;

@Service
@Slf4j
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ArquivoNotaFiscalSender arquivoSender;

    public void processar(String arquivoNotaFiscal) {
        try {
            final NfeProcXml xmlNotaFiscal = XmlRead.read(arquivoNotaFiscal);
            salvar(xmlNotaFiscal);
        } catch (FileException bex) {
            log.error("Erro no parser do arquivo", bex);
        } catch (BusinessException bex) {
            log.warn("Nota Fiscal já existente", bex);
        } catch (Exception ex) {
            enviarNotaFiscalParaFilaDeErro(arquivoNotaFiscal);
            log.error("Erro processar notaFisca", ex);
        }
    }

    @Transactional
    public void salvar(NfeProcXml xml) {
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

        notaFiscalRepository.save(notaFiscal);
    }

    private void validaNotaExistente(String numeroNf, Empresa emitente) {
        final NotaFiscal notaFiscal = notaFiscalRepository.findByEmitenteENumero(numeroNf, emitente.getId());
        if(notaFiscal != null) {
            throw new BusinessException(String.format("O Número %s de Nota fiscal já existente para o emitente %s (%s)",
                    numeroNf, emitente.getNome(), emitente.getCnpj()));
        }
    }

    private void enviarNotaFiscalParaFilaDeErro(String xml) {
        arquivoSender.send(xml);
    }

}
