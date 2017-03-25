CREATE TABLE nota_fiscal (
  id bigserial PRIMARY KEY NOT NULL,
  destinatario_id bigint NOT NULL,
  emitente_id bigint NOT NULL,
  numero_nf character varying(255) NOT NULL,
  data_emissao timestamp without time zone NOT NULL,
  data_hora_processamento timestamp without time zone NOT NULL,
  data_saida timestamp without time zone,
  valor numeric(19,2) NOT NULL,
  valor_desconto numeric(19,2),
  valor_total numeric(19,2) NOT NULL,
  CONSTRAINT destinatario_id_nota_fiscal_fk FOREIGN KEY (destinatario_id) REFERENCES empresa (id),
  CONSTRAINT emitente_id_nota_fiscal_fk FOREIGN KEY (emitente_id) REFERENCES empresa (id),
  CONSTRAINT nota_fiscal_uk UNIQUE (numero_nf, emitente_id)
);

CREATE TABLE item_nota_fiscal (
  id bigserial PRIMARY KEY NOT NULL,
  nota_fiscal_id bigint NOT NULL,
  produto_id bigint NOT NULL,
  numero_pedido character varying(255),
  numero_item integer,
  quantidade numeric(19,2),
  valor_produto numeric(19,2) NOT NULL,
  valor_total numeric(19,2) NOT NULL,
  CONSTRAINT nota_fiscal_id_item_nota_fiscal_fk FOREIGN KEY (nota_fiscal_id) REFERENCES nota_fiscal (id) DEFERRABLE INITIALLY DEFERRED,
  CONSTRAINT produto_id_item_nota_fiscal_fk FOREIGN KEY (produto_id) REFERENCES produto (id)
)