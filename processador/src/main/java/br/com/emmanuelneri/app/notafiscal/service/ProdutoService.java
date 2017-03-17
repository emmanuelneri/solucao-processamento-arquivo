package br.com.emmanuelneri.app.notafiscal.service;

import br.com.emmanuelneri.app.notafiscal.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.emmanuelneri.app.notafiscal.model.Empresa;
import br.com.emmanuelneri.app.notafiscal.model.Produto;
import br.com.emmanuelneri.app.notafiscal.xml.NfeDetXml;
import br.com.emmanuelneri.app.notafiscal.xml.NfeProdutoXml;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Map<String, Produto> findProdutoPorCodigoByfornecedor(List<NfeDetXml> dets, Empresa fornecedor) {
        final Set<String> codigosProdutos = getCodigoProduto(dets);
        return produtoRepository.findByCodigosEFornecedor(fornecedor, codigosProdutos)
                .stream().collect(Collectors.toMap(Produto::getCodigo, Function.identity()));
    }

    public Produto getProdutoSenaoCria(Map<String, Produto> produtoExistentesPorCodigo, NfeProdutoXml produtoXml, Empresa emitente) {
        final Produto produto = produtoExistentesPorCodigo.get(produtoXml.getcProd());

        if(produto != null) {
            return produto;
        }

        return cria(produtoXml, emitente);
    }

    private Set<String> getCodigoProduto(List<NfeDetXml> dets) {
        return  dets.stream()
                .flatMap(det -> det.getProdutos().stream())
                .map(NfeProdutoXml::getcProd)
                .collect(Collectors.toSet());
    }

    @Transactional
    public Produto cria(NfeProdutoXml produtoXml, Empresa emitente) {
        Produto produto = new Produto(produtoXml.getcProd(), produtoXml.getxProd(), emitente, produtoXml.getvUnTrib());
        produtoRepository.save(produto);
        return produto;
    }

}
