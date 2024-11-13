CREATE TABLE IF NOT EXISTS public.garcom
(
    id bigserial NOT NULL,
    cpf bigint,
    nome character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT garcom_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.item_pedido
(
    id bigserial NOT NULL,
    preco double precision,
    status boolean,
    idpedido bigint,
    idproduto bigint,
    CONSTRAINT item_pedido_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.mesa
(
    id bigserial NOT NULL,
    num_mesa integer,
    status boolean,
    CONSTRAINT mesa_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.pedido
(
    id bigserial NOT NULL,
    numpedido integer NOT NULL,
    status character varying(255) COLLATE pg_catalog."default",
    idgarcom bigint,
    idmesa bigint,
    CONSTRAINT pedido_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.produto
(
    id bigserial NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default",
    preco double precision,
    CONSTRAINT produto_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.item_pedido
    ADD CONSTRAINT fk7w48nwkv01iyr22cje2ja9bnd FOREIGN KEY (idproduto)
    REFERENCES public.produto (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.item_pedido
    ADD CONSTRAINT fkj6m352d4dwsig3xrho17c4jd0 FOREIGN KEY (idpedido)
    REFERENCES public.pedido (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.pedido
    ADD CONSTRAINT fka5wgeshv70jbat7u2o1c2me1k FOREIGN KEY (idgarcom)
    REFERENCES public.garcom (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.pedido
    ADD CONSTRAINT fkcsw6stxyrcrt1a7lmbvug145h FOREIGN KEY (idmesa)
    REFERENCES public.mesa (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;