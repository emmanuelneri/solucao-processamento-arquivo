CREATE TABLE produto (
  id bigserial PRIMARY KEY NOT NULL,
  fornecedor_id bigint NOT NULL,
  codigo character varying(255) NOT NULL,
  descricao character varying(200) NOT NULL,
  unidade character varying(100) NOT NULL,
  CONSTRAINT fornecedor_id_produto_fk FOREIGN KEY (fornecedor_id) REFERENCES empresa (id),
  CONSTRAINT produto_uk UNIQUE (codigo, fornecedor_id)
);