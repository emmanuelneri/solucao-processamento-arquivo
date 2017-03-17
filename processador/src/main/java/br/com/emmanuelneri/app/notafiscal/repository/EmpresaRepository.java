package br.com.emmanuelneri.app.notafiscal.repository;

import br.com.emmanuelneri.app.notafiscal.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Empresa findByCnpj(String cnpj);

}
