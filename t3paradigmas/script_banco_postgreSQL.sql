--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.4
-- Dumped by pg_dump version 9.1.4
-- Started on 2014-06-19 00:58:33

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 169 (class 3079 OID 11639)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1893 (class 0 OID 0)
-- Dependencies: 169
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 165 (class 1259 OID 16481)
-- Dependencies: 5
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE admin (
    matricula integer NOT NULL,
    senha character varying(20)
);


ALTER TABLE public.admin OWNER TO postgres;

--
-- TOC entry 162 (class 1259 OID 16464)
-- Dependencies: 5
-- Name: aluno; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE aluno (
    id integer NOT NULL,
    matricula integer,
    nome character varying(30),
    senha character varying(20),
    curso character varying(25),
    contato character varying(30),
    comp2 character varying(30),
    comp3 character varying(30),
    comp4 character varying(30)
);


ALTER TABLE public.aluno OWNER TO postgres;

--
-- TOC entry 161 (class 1259 OID 16462)
-- Dependencies: 5 162
-- Name: aluno_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE aluno_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.aluno_id_seq OWNER TO postgres;

--
-- TOC entry 1894 (class 0 OID 0)
-- Dependencies: 161
-- Name: aluno_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE aluno_id_seq OWNED BY aluno.id;


--
-- TOC entry 167 (class 1259 OID 16494)
-- Dependencies: 5
-- Name: bolsa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE bolsa (
    idbolsa integer NOT NULL,
    nomeorientador character varying(20),
    enderecoorientador character varying(20),
    duracao character varying(20)
);


ALTER TABLE public.bolsa OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 16502)
-- Dependencies: 5
-- Name: emprego; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE emprego (
    idemprego integer NOT NULL,
    beneficios character varying(20)
);


ALTER TABLE public.emprego OWNER TO postgres;

--
-- TOC entry 166 (class 1259 OID 16486)
-- Dependencies: 5
-- Name: estagio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE estagio (
    idestagio integer NOT NULL,
    orgao character varying(20)
);


ALTER TABLE public.estagio OWNER TO postgres;

--
-- TOC entry 164 (class 1259 OID 16472)
-- Dependencies: 5
-- Name: vaga; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE vaga (
    id integer NOT NULL,
    loc character varying(60),
    tipo character varying(20) NOT NULL,
    descricao text,
    titulo character varying(60),
    contato character varying(60),
    cargahoraria integer,
    valor integer
);


ALTER TABLE public.vaga OWNER TO postgres;

--
-- TOC entry 163 (class 1259 OID 16470)
-- Dependencies: 164 5
-- Name: vaga_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE vaga_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vaga_id_seq OWNER TO postgres;

--
-- TOC entry 1895 (class 0 OID 0)
-- Dependencies: 163
-- Name: vaga_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE vaga_id_seq OWNED BY vaga.id;


--
-- TOC entry 1871 (class 2604 OID 16467)
-- Dependencies: 161 162 162
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aluno ALTER COLUMN id SET DEFAULT nextval('aluno_id_seq'::regclass);


--
-- TOC entry 1872 (class 2604 OID 16475)
-- Dependencies: 164 163 164
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vaga ALTER COLUMN id SET DEFAULT nextval('vaga_id_seq'::regclass);


--
-- TOC entry 1878 (class 2606 OID 16485)
-- Dependencies: 165 165
-- Name: admin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (matricula);


--
-- TOC entry 1874 (class 2606 OID 16469)
-- Dependencies: 162 162
-- Name: aluno_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY aluno
    ADD CONSTRAINT aluno_pkey PRIMARY KEY (id);


--
-- TOC entry 1882 (class 2606 OID 16518)
-- Dependencies: 167 167
-- Name: bolsa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY bolsa
    ADD CONSTRAINT bolsa_pkey PRIMARY KEY (idbolsa);


--
-- TOC entry 1884 (class 2606 OID 16525)
-- Dependencies: 168 168
-- Name: emprego_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY emprego
    ADD CONSTRAINT emprego_pkey PRIMARY KEY (idemprego);


--
-- TOC entry 1880 (class 2606 OID 16532)
-- Dependencies: 166 166
-- Name: estagio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY estagio
    ADD CONSTRAINT estagio_pkey PRIMARY KEY (idestagio);


--
-- TOC entry 1876 (class 2606 OID 16480)
-- Dependencies: 164 164
-- Name: vaga_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY vaga
    ADD CONSTRAINT vaga_pkey PRIMARY KEY (id);


--
-- TOC entry 1886 (class 2606 OID 16512)
-- Dependencies: 1875 164 167
-- Name: bolsa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bolsa
    ADD CONSTRAINT bolsa_fkey FOREIGN KEY (idbolsa) REFERENCES vaga(id);


--
-- TOC entry 1887 (class 2606 OID 16519)
-- Dependencies: 164 168 1875
-- Name: emprego_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY emprego
    ADD CONSTRAINT emprego_fkey FOREIGN KEY (idemprego) REFERENCES vaga(id);


--
-- TOC entry 1885 (class 2606 OID 16526)
-- Dependencies: 1875 164 166
-- Name: estagio_pkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--


ALTER TABLE estagio
  ADD CONSTRAINT idestagio FOREIGN KEY (idestagio)
      REFERENCES vaga (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
--
-- TOC entry 1892 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-06-19 00:58:33

--
-- PostgreSQL database dump complete
--

