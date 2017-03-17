package br.com.emmanuelneri.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.emmanuelneri.app.model.Empresa;
import br.com.emmanuelneri.app.model.Produto;
import br.com.emmanuelneri.app.xml.NfeDetXml;
import br.com.emmanuelneri.app.xml.NfeProdutoXml;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProdutoService extends AbstractService{

    public Map<String, Produto> findProdutoPorCodigoByfornecedor(List<NfeDetXml> dets, Empresa fornecedor) {
        final Set<String> codigosProdutos = getCodigoProduto(dets);
        return findByCodigoEFornecedor(codigosProdutos, fornecedor)
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

    private List<Produto> findByCodigoEFornecedor(Set<String> codigos, Empresa fornecedor) {
        return getEntityManager().createNamedQuery("Produto.findByCodigosEFornecedor", Produto.class)
                .setParameter("codigos", codigos)
                .setParameter("fornecedor", fornecedor)
                .getResultList();
    }

    @Transactional
    public Produto cria(NfeProdutoXml produtoXml, Empresa emitente) {
        Produto produto = new Produto(produtoXml.getcProd(), produtoXml.getxProd(), emitente, produtoXml.getvUnTrib());
        persist(produto);
        return produto;
    }

}
