package br.com.emmanuelneri.app.notafiscal.repository;

import br.com.emmanuelneri.app.notafiscal.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {

    NotaFiscal findByEmitenteENumero(String numeroNf, Long idEmpresaEmitente);

}
