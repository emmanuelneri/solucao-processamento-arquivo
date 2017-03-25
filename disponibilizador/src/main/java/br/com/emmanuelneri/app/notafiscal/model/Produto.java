package br.com.emmanuelneri.app.notafiscal.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String descricao;
    private String unidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FORNECEDOR_ID")
    private Empresa fornecedor;

}
