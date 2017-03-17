package br.com.emmanuelneri.app.notafiscal.repository;

import br.com.emmanuelneri.app.notafiscal.model.Empresa;
import br.com.emmanuelneri.app.notafiscal.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCodigosEFornecedor(Empresa fornecedor, Set<String> codigos);
}
