package br.com.emmanuelneri.app.notafiscal.repository;

import br.com.emmanuelneri.app.notafiscal.dto.NotaFiscalDTO;
import br.com.emmanuelneri.app.notafiscal.mapper.NotaFiscalResultSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotaFiscalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<NotaFiscalDTO> findAll() {
        return jdbcTemplate.query(
                "SELECT nf.id as id_nf, nf.numero_nf, nf.data_emissao, nf.valor, nf.valor_desconto, nf.valor_total," +
                        " emitente.cnpj as cnpj_emitente, emitente.nome as nome_emitente, " +
                        " destinatario.cnpj as cnpj_destinatario, destinatario.nome as nome_destinatario, " +
                        " item.numero_pedido, item.numero_item, item.quantidade, item.valor_produto, item.valor_total," +
                        " produto.codigo, produto.descricao, produto.unidade" +
                        " FROM nota_fiscal nf " +
                        " inner join empresa emitente on emitente.id = nf.emitente_id" +
                        " inner join empresa destinatario on destinatario.id = nf.destinatario_id" +
                        " inner join item_nota_fiscal item on item.nota_fiscal_id = nf.id" +
                        " inner join produto produto on produto.id = item.produto_id" +
                        " order by nf.data_emissao desc, emitente.nome", NotaFiscalResultSetExtractor.listExtractor()
        );
    }

    public NotaFiscalDTO findByCnpjEmitenteENumeroNotaFiscal(String cnpjEmitente, String numeroNotaFiscal) {
        return jdbcTemplate.query(
                "SELECT nf.id as id_nf, nf.numero_nf, nf.data_emissao, nf.valor, nf.valor_desconto, nf.valor_total," +
                        " emitente.cnpj as cnpj_emitente, emitente.nome as nome_emitente, " +
                        " destinatario.cnpj as cnpj_destinatario, destinatario.nome as nome_destinatario, " +
                        " item.numero_pedido, item.numero_item, item.quantidade, item.valor_produto, item.valor_total," +
                        " produto.codigo, produto.descricao, produto.unidade" +
                        " FROM nota_fiscal nf " +
                        " inner join empresa emitente on emitente.id = nf.emitente_id" +
                        " inner join empresa destinatario on destinatario.id = nf.destinatario_id" +
                        " inner join item_nota_fiscal item on item.nota_fiscal_id = nf.id" +
                        " inner join produto produto on produto.id = item.produto_id" +
                        " where emitente.cnpj = ? and nf.numero_nf = ?",
                    new Object[] {cnpjEmitente, numeroNotaFiscal},  NotaFiscalResultSetExtractor.entityExtractor()
        );
    }
}
