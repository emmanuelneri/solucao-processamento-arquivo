CREATE TABLE empresa (
  id bigserial PRIMARY KEY NOT NULL,
  cnpj character varying(14) NOT NULL,
  nome character varying(200) NOT NULL,
  tipo_empresa character varying(50) NOT NULL,
  CONSTRAINT empresa_uk UNIQUE (cnpj)
);