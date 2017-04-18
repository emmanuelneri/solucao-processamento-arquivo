package br.com.emmanuelneri.app.arquivo.service;

import br.com.emmanuelneri.app.arquivo.repository.MongoDBRepository;
import br.com.emmanuelneri.app.notafiscal.dto.ArquivoDTO;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.emmanuelneri.app.notafiscal.dto.ArquivoDTO.ARQUIVO_ID;
import static br.com.emmanuelneri.app.notafiscal.dto.ArquivoDTO.criar;
import static br.com.emmanuelneri.app.notafiscal.dto.ArquivoDTO.criarComConteudo;

@Service
public class NotaFiscalArquivoService {

    @Autowired
    private MongoDBRepository mongoDBRepository;

    public List<ArquivoDTO> findAll() {
        final List<ArquivoDTO> arquivoDTOS = new ArrayList<>();
        mongoDBRepository.find().forEach(document -> arquivoDTOS.add(criar(document)));
        return arquivoDTOS;
    }

    public ArquivoDTO findById(String id) {
        final Document document = mongoDBRepository.findFirst(new Document(ARQUIVO_ID, new ObjectId(id)));
        return criarComConteudo(document);
    }

}
