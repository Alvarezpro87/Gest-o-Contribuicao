CREATE TABLE IF NOT EXISTS public.aliquota
(
    id bigserial NOT NULL,
    categoria character varying(255) NOT NULL,
    salario_inicio numeric(10, 2) NOT NULL,
    salario_fim numeric(10, 2) NOT NULL,
    valor_aliquota numeric(5, 2) NOT NULL,
    CONSTRAINT aliquota_pkey PRIMARY KEY (id),
    CONSTRAINT uniq_aliquota UNIQUE (categoria, salario_inicio, salario_fim, valor_aliquota)
    );


CREATE SEQUENCE salario_minimo_historico_id_seq;
CREATE TABLE IF NOT EXISTS public.salario_minimo_historico
(
    id bigint NOT NULL DEFAULT nextval('salario_minimo_historico_id_seq'),
    data_minimo date NOT NULL,
    valor_salario_minimo_ano numeric(10, 2) NOT NULL,
    CONSTRAINT salario_minimo_historico_pkey PRIMARY KEY (id)
);

