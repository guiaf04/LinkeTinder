--
-- PostgreSQL database dump
--

-- Dumped from database version 14.15 (Ubuntu 14.15-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.15 (Ubuntu 14.15-0ubuntu0.22.04.1)

-- Name: candidato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS candidato (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    sobrenome character varying(100) NOT NULL,
    data_nascimento date NOT NULL,
    email character varying(255) NOT NULL,
    cpf character varying(20) NOT NULL,
    pais character varying(100) NOT NULL,
    cep character varying(20) NOT NULL,
    descricao_pessoal character varying(255) NOT NULL,
    senha character varying(40) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS candidato_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: candidato_competencia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS candidato_competencia (
    id integer NOT NULL,
    id_candidato integer NOT NULL,
    id_competencia integer NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS candidato_competencia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Name: candidato_vaga; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS candidato_vaga_match (
  id integer NOT NULL ,
  id_candidato integer NOT NULL,
  id_vaga integer NOT NULL,
  candidato_liked BOOLEAN DEFAULT FALSE,
  empresa_liked BOOLEAN DEFAULT FALSE,
  match BOOLEAN DEFAULT FALSE,
  UNIQUE (id_candidato, id_vaga)
);


--
-- Name: candidato_vaga_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE IF NOT EXISTS candidato_vaga_match_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE candidato_vaga_match_id_seq OWNER TO postgres;

--
-- Name: candidato_vaga_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE candidato_vaga_match_id_seq OWNED BY candidato_vaga_match.id;


--
-- Name: competencia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS competencia (
                             id integer NOT NULL,
                             nome character varying(100) NOT NULL
);


ALTER TABLE competencia OWNER TO postgres;

--
-- Name: competencia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE IF NOT EXISTS competencia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE competencia_id_seq OWNER TO postgres;

--
-- Name: competencia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE competencia_id_seq OWNED BY competencia.id;


--
-- Name: empresa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS empresa (
                         id integer NOT NULL,
                         nome character varying(100) NOT NULL,
                         email character varying(100) NOT NULL,
                         cnpj character varying(20) NOT NULL,
                         pais character varying(100) NOT NULL,
                         cep character varying(20) NOT NULL,
                         descricao character varying(255) NOT NULL,
                         senha character varying(40) NOT NULL
);


ALTER TABLE empresa OWNER TO postgres;

--
-- Name: empresa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE IF NOT EXISTS empresa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa_id_seq OWNER TO postgres;

--
-- Name: empresa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE empresa_id_seq OWNED BY empresa.id;


--
-- Name: vaga; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS vaga (
                      id integer NOT NULL,
                      nome character varying(255) NOT NULL,
                      descricao character varying(255) NOT NULL,
                      local character varying(255) NOT NULL,
                      id_empresa integer
);


ALTER TABLE vaga OWNER TO postgres;

--
-- Name: vaga_competencia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS vaga_competencia (
                                  id integer NOT NULL,
                                  id_vaga integer NOT NULL,
                                  id_competencia integer NOT NULL
);


ALTER TABLE vaga_competencia OWNER TO postgres;

--
-- Name: vaga_competencia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE IF NOT EXISTS vaga_competencia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vaga_competencia_id_seq OWNER TO postgres;

--
-- Name: vaga_competencia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE vaga_competencia_id_seq OWNED BY vaga_competencia.id;


--
-- Name: vaga_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE IF NOT EXISTS vaga_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vaga_id_seq OWNER TO postgres;

--
-- Name: vaga_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE vaga_id_seq OWNED BY vaga.id;


--
-- Name: candidato id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato ALTER COLUMN id SET DEFAULT nextval('candidato_id_seq'::regclass);


--
-- Name: candidato_competencia id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato_competencia ALTER COLUMN id SET DEFAULT nextval('candidato_competencia_id_seq'::regclass);


--
-- Name: candidato_vaga id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato_vaga_match ALTER COLUMN id SET DEFAULT nextval('candidato_vaga_match_id_seq'::regclass);


--
-- Name: competencia id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY competencia ALTER COLUMN id SET DEFAULT nextval('competencia_id_seq'::regclass);


--
-- Name: empresa id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresa ALTER COLUMN id SET DEFAULT nextval('empresa_id_seq'::regclass);


--
-- Name: vaga id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vaga ALTER COLUMN id SET DEFAULT nextval('vaga_id_seq'::regclass);


--
-- Name: vaga_competencia id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vaga_competencia ALTER COLUMN id SET DEFAULT nextval('vaga_competencia_id_seq'::regclass);


--
-- Data for Name: vaga_competencia; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: candidato_competencia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('candidato_competencia_id_seq', 13, true);


--
-- Name: candidato_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('candidato_id_seq', 22, true);


--
-- Name: candidato_vaga_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('candidato_vaga_match_id_seq', 1, false);


--
-- Name: competencia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('competencia_id_seq', 4, true);


--
-- Name: empresa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('empresa_id_seq', 9, true);


--
-- Name: vaga_competencia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('vaga_competencia_id_seq', 1, false);


--
-- Name: vaga_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('vaga_id_seq', 15, true);


--
-- Name: candidato_competencia candidato_competencia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato_competencia
    ADD CONSTRAINT candidato_competencia_pkey PRIMARY KEY (id);


--
-- Name: candidato candidato_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato
    ADD CONSTRAINT candidato_pkey PRIMARY KEY (id);


--
-- Name: candidato_vaga candidato_vaga_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato_vaga_match
    ADD CONSTRAINT candidato_vaga_match_pkey PRIMARY KEY (id);


--
-- Name: competencia competencia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY competencia
    ADD CONSTRAINT competencia_pkey PRIMARY KEY (id);


--
-- Name: empresa empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (id);


--
-- Name: vaga_competencia vaga_competencia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vaga_competencia
    ADD CONSTRAINT vaga_competencia_pkey PRIMARY KEY (id);


--
-- Name: vaga vaga_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vaga
    ADD CONSTRAINT vaga_pkey PRIMARY KEY (id);


--
-- Name: candidato_competencia candidato_competencia_id_candidato_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato_competencia
    ADD CONSTRAINT candidato_competencia_id_candidato_fkey FOREIGN KEY (id_candidato) REFERENCES candidato(id);


--
-- Name: candidato_competencia candidato_competencia_id_competencia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato_competencia
    ADD CONSTRAINT candidato_competencia_id_competencia_fkey FOREIGN KEY (id_competencia) REFERENCES competencia(id);


--
-- Name: candidato_vaga candidato_vaga_id_candidato_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato_vaga_match
    ADD CONSTRAINT candidato_vaga_match_id_candidato_fkey FOREIGN KEY (id_candidato) REFERENCES candidato(id);


--
-- Name: candidato_vaga candidato_vaga_id_vafa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato_vaga_match
    ADD CONSTRAINT candidato_vaga_match_id_vaga_fkey FOREIGN KEY (id_vaga) REFERENCES vaga(id);


--
-- Name: vaga_competencia vaga_competencia_id_competencia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vaga_competencia
    ADD CONSTRAINT vaga_competencia_id_competencia_fkey FOREIGN KEY (id_competencia) REFERENCES competencia(id);


--
-- Name: vaga_competencia vaga_competencia_id_vaga_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vaga_competencia
    ADD CONSTRAINT vaga_competencia_id_vaga_fkey FOREIGN KEY (id_vaga) REFERENCES vaga(id);


--
-- Name: vaga vaga_id_empresa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vaga
    ADD CONSTRAINT vaga_id_empresa_fkey FOREIGN KEY (id_empresa) REFERENCES empresa(id);


--
-- PostgreSQL database dump complete
--

