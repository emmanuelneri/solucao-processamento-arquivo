package br.com.emmanuelneri.app.repository;

import br.com.emmanuelneri.app.model.NotaFiscalXml;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotaFiscalXmlRepository extends MongoRepository<NotaFiscalXml, String> {
}
