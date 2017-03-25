package br.com.emmanuelneri.app.notafiscal.repository;

import br.com.emmanuelneri.app.notafiscal.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long>{

    NotaFiscal findByCnpjEmitenteENumeroNotaFiscal(String cnpjEmitente, String numeroNotaFiscal);

    List<NotaFiscal> findAll();

}
