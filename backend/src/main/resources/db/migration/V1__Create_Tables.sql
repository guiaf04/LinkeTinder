--
-- PostgreSQL database dump
--

-- Dumped from database version 14.15 (Ubuntu 14.15-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.15 (Ubuntu 14.15-0ubuntu0.22.04.1)

-- Name: candidato; Type: TABLE; Schema: public; Owner: gui
--

CREATE TABLE IF NOT EXISTS candidato (
    id bigint NOT NULL,
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

--
-- Name: candidato_competencia; Type: TABLE; Schema: public; Owner: gui
--

CREATE TABLE IF NOT EXISTS candidato_competencia (
    id integer NOT NULL,
    id_candidato integer NOT NULL,
    id_competencia integer NOT NULL
);


-- Name: candidato_vaga; Type: TABLE; Schema: public; Owner: gui
--

CREATE TABLE IF NOT EXISTS candidato_vaga (
    id integer NOT NULL,
    id_candidato integer,
    id_vafa integer
);


--
-- Name: competencia; Type: TABLE; Schema: public;
--

CREATE TABLE IF NOT EXISTS competencia (
    id integer NOT NULL,
    nome character varying(100) NOT NULL
);

--
-- Name: empresa; Type: TABLE; Schema: public;
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

-- Name: empresa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: gui
--


--
-- Name: vaga; Type: TABLE; Schema: public; Owner: gui
--

CREATE TABLE IF NOT EXISTS vaga (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    descricao character varying(255) NOT NULL,
    local character varying(255) NOT NULL,
    id_empresa integer
);

--
-- Name: vaga_competencia; Type: TABLE; Schema: public; Owner: gui
--

CREATE TABLE IF NOT EXISTS vaga_competencia (
    id integer NOT NULL,
    id_vaga integer NOT NULL,
    id_competencia integer NOT NULL
);

--
-- Name: candidato_competencia candidato_competencia_pkey; Type: CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY candidato_competencia
    ADD CONSTRAINT candidato_competencia_pkey PRIMARY KEY (id);


--
-- Name: candidato candidato_pkey; Type: CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY candidato
    ADD CONSTRAINT candidato_pkey PRIMARY KEY (id);


--
-- Name: candidato_vaga candidato_vaga_pkey; Type: CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY candidato_vaga
    ADD CONSTRAINT candidato_vaga_pkey PRIMARY KEY (id);


--
-- Name: competencia competencia_pkey; Type: CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY competencia
    ADD CONSTRAINT competencia_pkey PRIMARY KEY (id);


--
-- Name: empresa empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (id);


--
-- Name: vaga_competencia vaga_competencia_pkey; Type: CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY vaga_competencia
    ADD CONSTRAINT vaga_competencia_pkey PRIMARY KEY (id);


--
-- Name: vaga vaga_pkey; Type: CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY vaga
    ADD CONSTRAINT vaga_pkey PRIMARY KEY (id);


--
-- Name: candidato_competencia candidato_competencia_id_candidato_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY candidato_competencia
    ADD CONSTRAINT candidato_competencia_id_candidato_fkey FOREIGN KEY (id_candidato) REFERENCES candidato(id);


--
-- Name: candidato_competencia candidato_competencia_id_competencia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY candidato_competencia
    ADD CONSTRAINT candidato_competencia_id_competencia_fkey FOREIGN KEY (id_competencia) REFERENCES competencia(id);


--
-- Name: candidato_vaga candidato_vaga_id_candidato_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY candidato_vaga
    ADD CONSTRAINT candidato_vaga_id_candidato_fkey FOREIGN KEY (id_candidato) REFERENCES candidato(id);


--
-- Name: candidato_vaga candidato_vaga_id_vafa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY candidato_vaga
    ADD CONSTRAINT candidato_vaga_id_vafa_fkey FOREIGN KEY (id_vafa) REFERENCES vaga(id);


--
-- Name: vaga_competencia vaga_competencia_id_competencia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY vaga_competencia
    ADD CONSTRAINT vaga_competencia_id_competencia_fkey FOREIGN KEY (id_competencia) REFERENCES competencia(id);


--
-- Name: vaga_competencia vaga_competencia_id_vaga_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY vaga_competencia
    ADD CONSTRAINT vaga_competencia_id_vaga_fkey FOREIGN KEY (id_vaga) REFERENCES vaga(id);


--
-- Name: vaga vaga_id_empresa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gui
--

ALTER TABLE ONLY vaga
    ADD CONSTRAINT vaga_id_empresa_fkey FOREIGN KEY (id_empresa) REFERENCES empresa(id);

