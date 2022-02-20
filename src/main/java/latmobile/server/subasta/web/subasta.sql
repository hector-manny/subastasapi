--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.23
-- Dumped by pg_dump version 14.2

-- Started on 2022-02-18 16:33:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

--CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2331 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

--
-- TOC entry 185 (class 1259 OID 16386)
-- Name: departamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.departamento (
                                     id_departamento integer NOT NULL,
                                     isocode character varying(500) NOT NULL,
                                     nombre character varying(55) NOT NULL,
                                     zonesv_id smallint
);


ALTER TABLE public.departamento OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16392)
-- Name: departamento_id_departamento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.departamento_id_departamento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.departamento_id_departamento_seq OWNER TO postgres;

--
-- TOC entry 2332 (class 0 OID 0)
-- Dependencies: 186
-- Name: departamento_id_departamento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.departamento_id_departamento_seq OWNED BY public.departamento.id_departamento;


--
-- TOC entry 187 (class 1259 OID 16394)
-- Name: estado_lote; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estado_lote (
                                    id_estado_lote integer NOT NULL,
                                    activo smallint NOT NULL,
                                    descripcion character varying(500) NOT NULL,
                                    nombre character varying(55) NOT NULL
);


ALTER TABLE public.estado_lote OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16400)
-- Name: estado_lote_id_estado_lote_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estado_lote_id_estado_lote_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_lote_id_estado_lote_seq OWNER TO postgres;

--
-- TOC entry 2333 (class 0 OID 0)
-- Dependencies: 188
-- Name: estado_lote_id_estado_lote_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estado_lote_id_estado_lote_seq OWNED BY public.estado_lote.id_estado_lote;


--
-- TOC entry 189 (class 1259 OID 16402)
-- Name: estado_sub; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estado_sub (
                                   id_estado_sub integer NOT NULL,
                                   activo smallint NOT NULL,
                                   descripcion character varying(500) NOT NULL,
                                   nombre character varying(55) NOT NULL
);


ALTER TABLE public.estado_sub OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16408)
-- Name: estado_sub_id_estado_sub_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estado_sub_id_estado_sub_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_sub_id_estado_sub_seq OWNER TO postgres;

--
-- TOC entry 2334 (class 0 OID 0)
-- Dependencies: 190
-- Name: estado_sub_id_estado_sub_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estado_sub_id_estado_sub_seq OWNED BY public.estado_sub.id_estado_sub;


--
-- TOC entry 191 (class 1259 OID 16410)
-- Name: estado_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estado_usuario (
                                       id_estado_usuario integer NOT NULL,
                                       activo smallint NOT NULL,
                                       descripcion character varying(500) NOT NULL,
                                       nombre character varying(55) NOT NULL
);


ALTER TABLE public.estado_usuario OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16416)
-- Name: estado_usuario_id_estado_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estado_usuario_id_estado_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_usuario_id_estado_usuario_seq OWNER TO postgres;

--
-- TOC entry 2335 (class 0 OID 0)
-- Dependencies: 192
-- Name: estado_usuario_id_estado_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estado_usuario_id_estado_usuario_seq OWNED BY public.estado_usuario.id_estado_usuario;


--
-- TOC entry 193 (class 1259 OID 16418)
-- Name: image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image (
                              id_image integer NOT NULL,
                              orden integer,
                              src character varying(200) NOT NULL,
                              id_lote integer
);


ALTER TABLE public.image OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 16421)
-- Name: image_id_image_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.image_id_image_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.image_id_image_seq OWNER TO postgres;

--
-- TOC entry 2336 (class 0 OID 0)
-- Dependencies: 194
-- Name: image_id_image_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.image_id_image_seq OWNED BY public.image.id_image;


--
-- TOC entry 195 (class 1259 OID 16423)
-- Name: lote; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lote (
                             id_lote integer NOT NULL,
                             descripcion character varying(500),
                             impuestos character varying(50),
                             medidas character varying(100),
                             nombre character varying(150),
                             peso character varying(50),
                             precio numeric(8,2),
                             id_estado_lote integer,
                             id_subasta integer,
                             id_tipo_lote integer,
                             fecha_fin timestamp without time zone,
                             fecha_inicio timestamp without time zone,
                             hora_fin timestamp without time zone,
                             hora_inicio timestamp without time zone,
                             id_ganador integer,
                             fecha_ganador timestamp without time zone,
                             delete smallint,
                             porcentaje_reserva numeric(8,2)
);


ALTER TABLE public.lote OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16429)
-- Name: lote_id_lote_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lote_id_lote_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lote_id_lote_seq OWNER TO postgres;

--
-- TOC entry 2337 (class 0 OID 0)
-- Dependencies: 196
-- Name: lote_id_lote_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lote_id_lote_seq OWNED BY public.lote.id_lote;


--
-- TOC entry 197 (class 1259 OID 16431)
-- Name: lote_participantes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lote_participantes (
                                           id_lote_participantes integer NOT NULL,
                                           id_usuario integer,
                                           paso integer,
                                           id_lote integer
);


ALTER TABLE public.lote_participantes OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16434)
-- Name: lote_participantes_id_lote_participantes_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lote_participantes_id_lote_participantes_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lote_participantes_id_lote_participantes_seq OWNER TO postgres;

--
-- TOC entry 2338 (class 0 OID 0)
-- Dependencies: 198
-- Name: lote_participantes_id_lote_participantes_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lote_participantes_id_lote_participantes_seq OWNED BY public.lote_participantes.id_lote_participantes;


--
-- TOC entry 199 (class 1259 OID 16436)
-- Name: municipio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.municipio (
                                  id_municipio integer NOT NULL,
                                  nombre character varying(55) NOT NULL,
                                  id_departamento integer
);


ALTER TABLE public.municipio OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16439)
-- Name: municipio_id_municipio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.municipio_id_municipio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.municipio_id_municipio_seq OWNER TO postgres;

--
-- TOC entry 2339 (class 0 OID 0)
-- Dependencies: 200
-- Name: municipio_id_municipio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.municipio_id_municipio_seq OWNED BY public.municipio.id_municipio;


--
-- TOC entry 201 (class 1259 OID 16441)
-- Name: parametro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.parametro (
                                  id_estado_parametro integer NOT NULL,
                                  activo smallint NOT NULL,
                                  descripcion character varying(500) NOT NULL,
                                  nombre character varying(55) NOT NULL,
                                  valor character varying(500) NOT NULL
);


ALTER TABLE public.parametro OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16447)
-- Name: parametro_id_estado_parametro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.parametro_id_estado_parametro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.parametro_id_estado_parametro_seq OWNER TO postgres;

--
-- TOC entry 2340 (class 0 OID 0)
-- Dependencies: 202
-- Name: parametro_id_estado_parametro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.parametro_id_estado_parametro_seq OWNED BY public.parametro.id_estado_parametro;


--
-- TOC entry 203 (class 1259 OID 16449)
-- Name: permiso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permiso (
                                id_permiso integer NOT NULL,
                                activo smallint NOT NULL,
                                descripcion character varying(500) NOT NULL,
                                nombre character varying(55) NOT NULL
);


ALTER TABLE public.permiso OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16455)
-- Name: permiso_id_permiso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.permiso_id_permiso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permiso_id_permiso_seq OWNER TO postgres;

--
-- TOC entry 2341 (class 0 OID 0)
-- Dependencies: 204
-- Name: permiso_id_permiso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.permiso_id_permiso_seq OWNED BY public.permiso.id_permiso;


--
-- TOC entry 205 (class 1259 OID 16457)
-- Name: puja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.puja (
                             id_puja integer NOT NULL,
                             fecha timestamp without time zone,
                             monto numeric(8,2),
                             id_lote integer,
                             id_usuario integer
);


ALTER TABLE public.puja OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16460)
-- Name: puja_id_puja_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.puja_id_puja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.puja_id_puja_seq OWNER TO postgres;

--
-- TOC entry 2342 (class 0 OID 0)
-- Dependencies: 206
-- Name: puja_id_puja_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.puja_id_puja_seq OWNED BY public.puja.id_puja;


--
-- TOC entry 207 (class 1259 OID 16462)
-- Name: rol; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rol (
                            id_rol integer NOT NULL,
                            activo smallint NOT NULL,
                            descripcion character varying(500) NOT NULL,
                            nombre character varying(55) NOT NULL
);


ALTER TABLE public.rol OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16468)
-- Name: rol_id_rol_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rol_id_rol_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rol_id_rol_seq OWNER TO postgres;

--
-- TOC entry 2343 (class 0 OID 0)
-- Dependencies: 208
-- Name: rol_id_rol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rol_id_rol_seq OWNED BY public.rol.id_rol;


--
-- TOC entry 209 (class 1259 OID 16470)
-- Name: rol_permiso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rol_permiso (
                                    id_rol_permiso integer NOT NULL,
                                    id_permiso integer,
                                    id_rol integer
);


ALTER TABLE public.rol_permiso OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16473)
-- Name: rol_permiso_id_rol_permiso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rol_permiso_id_rol_permiso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rol_permiso_id_rol_permiso_seq OWNER TO postgres;

--
-- TOC entry 2344 (class 0 OID 0)
-- Dependencies: 210
-- Name: rol_permiso_id_rol_permiso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rol_permiso_id_rol_permiso_seq OWNED BY public.rol_permiso.id_rol_permiso;


--
-- TOC entry 211 (class 1259 OID 16475)
-- Name: subasta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subasta (
                                id_subasta integer NOT NULL,
                                aumento numeric(8,2),
                                fecha_fin_suscripcion timestamp without time zone,
                                fecha_inicio timestamp without time zone,
                                fecha_inicio_suscripcion timestamp without time zone,
                                hora_fin timestamp without time zone,
                                hora_inicio timestamp without time zone,
                                precio_inicio numeric(8,2),
                                fecha_fin timestamp without time zone,
                                hora_defecto timestamp without time zone,
                                descripcion character varying(500),
                                nombre character varying(150),
                                hora_inicio_suscripcion timestamp without time zone,
                                hora_fin_suscripcion timestamp without time zone,
                                activo smallint,
                                delete smallint
);


ALTER TABLE public.subasta OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16481)
-- Name: subasta_id_subasta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.subasta_id_subasta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.subasta_id_subasta_seq OWNER TO postgres;

--
-- TOC entry 2345 (class 0 OID 0)
-- Dependencies: 212
-- Name: subasta_id_subasta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subasta_id_subasta_seq OWNED BY public.subasta.id_subasta;


--
-- TOC entry 213 (class 1259 OID 16483)
-- Name: tipo_lote; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_lote (
                                  id_tipo_lote integer NOT NULL,
                                  activo smallint NOT NULL,
                                  descripcion character varying(500) NOT NULL,
                                  nombre character varying(55) NOT NULL
);


ALTER TABLE public.tipo_lote OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16489)
-- Name: tipo_lote_id_tipo_lote_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_lote_id_tipo_lote_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_lote_id_tipo_lote_seq OWNER TO postgres;

--
-- TOC entry 2346 (class 0 OID 0)
-- Dependencies: 214
-- Name: tipo_lote_id_tipo_lote_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_lote_id_tipo_lote_seq OWNED BY public.tipo_lote.id_tipo_lote;


--
-- TOC entry 215 (class 1259 OID 16491)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
                                id_usuario integer NOT NULL,
                                codigo character varying(6),
                                correo character varying(55) NOT NULL,
                                departamento character varying(100) NOT NULL,
                                direccion character varying(300) NOT NULL,
                                dui character varying(12) NOT NULL,
                                municipio character varying(100) NOT NULL,
                                nit character varying(20) NOT NULL,
                                nombre character varying(55) NOT NULL,
                                password character varying(255) NOT NULL,
                                telefono character varying(10) NOT NULL,
                                username character varying(55) NOT NULL,
                                id_estado_usuario integer,
                                id_rol integer,
                                token character varying(100)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16497)
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_usuario_seq OWNER TO postgres;

--
-- TOC entry 2347 (class 0 OID 0)
-- Dependencies: 216
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;


--
-- TOC entry 217 (class 1259 OID 16499)
-- Name: usuario_suscripcion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_suscripcion (
                                            id_usuario_suscripcion integer NOT NULL,
                                            banco character varying(200),
                                            fecha_adjudicacion timestamp without time zone,
                                            identificacion_tansaccion character varying(300),
                                            monto_maximo_puja numeric(8,2),
                                            porcentaje_reserva numeric(8,2),
                                            reserva numeric(8,2),
                                            transaccion character varying(300),
                                            id_estado_sub integer,
                                            id_usuario integer,
                                            id_lote integer,
                                            comprobante character varying(300),
                                            valid smallint
);


ALTER TABLE public.usuario_suscripcion OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16505)
-- Name: usuario_suscripcion_id_usuario_suscripcion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_suscripcion_id_usuario_suscripcion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_suscripcion_id_usuario_suscripcion_seq OWNER TO postgres;

--
-- TOC entry 2348 (class 0 OID 0)
-- Dependencies: 218
-- Name: usuario_suscripcion_id_usuario_suscripcion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_suscripcion_id_usuario_suscripcion_seq OWNED BY public.usuario_suscripcion.id_usuario_suscripcion;


--
-- TOC entry 2110 (class 2604 OID 16507)
-- Name: departamento id_departamento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departamento ALTER COLUMN id_departamento SET DEFAULT nextval('public.departamento_id_departamento_seq'::regclass);


--
-- TOC entry 2111 (class 2604 OID 16508)
-- Name: estado_lote id_estado_lote; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_lote ALTER COLUMN id_estado_lote SET DEFAULT nextval('public.estado_lote_id_estado_lote_seq'::regclass);


--
-- TOC entry 2112 (class 2604 OID 16509)
-- Name: estado_sub id_estado_sub; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_sub ALTER COLUMN id_estado_sub SET DEFAULT nextval('public.estado_sub_id_estado_sub_seq'::regclass);


--
-- TOC entry 2113 (class 2604 OID 16510)
-- Name: estado_usuario id_estado_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_usuario ALTER COLUMN id_estado_usuario SET DEFAULT nextval('public.estado_usuario_id_estado_usuario_seq'::regclass);


--
-- TOC entry 2114 (class 2604 OID 16511)
-- Name: image id_image; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image ALTER COLUMN id_image SET DEFAULT nextval('public.image_id_image_seq'::regclass);


--
-- TOC entry 2115 (class 2604 OID 16512)
-- Name: lote id_lote; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote ALTER COLUMN id_lote SET DEFAULT nextval('public.lote_id_lote_seq'::regclass);


--
-- TOC entry 2116 (class 2604 OID 16513)
-- Name: lote_participantes id_lote_participantes; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote_participantes ALTER COLUMN id_lote_participantes SET DEFAULT nextval('public.lote_participantes_id_lote_participantes_seq'::regclass);


--
-- TOC entry 2117 (class 2604 OID 16514)
-- Name: municipio id_municipio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.municipio ALTER COLUMN id_municipio SET DEFAULT nextval('public.municipio_id_municipio_seq'::regclass);


--
-- TOC entry 2118 (class 2604 OID 16515)
-- Name: parametro id_estado_parametro; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parametro ALTER COLUMN id_estado_parametro SET DEFAULT nextval('public.parametro_id_estado_parametro_seq'::regclass);


--
-- TOC entry 2119 (class 2604 OID 16516)
-- Name: permiso id_permiso; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permiso ALTER COLUMN id_permiso SET DEFAULT nextval('public.permiso_id_permiso_seq'::regclass);


--
-- TOC entry 2120 (class 2604 OID 16517)
-- Name: puja id_puja; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.puja ALTER COLUMN id_puja SET DEFAULT nextval('public.puja_id_puja_seq'::regclass);


--
-- TOC entry 2121 (class 2604 OID 16518)
-- Name: rol id_rol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol ALTER COLUMN id_rol SET DEFAULT nextval('public.rol_id_rol_seq'::regclass);


--
-- TOC entry 2122 (class 2604 OID 16519)
-- Name: rol_permiso id_rol_permiso; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol_permiso ALTER COLUMN id_rol_permiso SET DEFAULT nextval('public.rol_permiso_id_rol_permiso_seq'::regclass);


--
-- TOC entry 2123 (class 2604 OID 16520)
-- Name: subasta id_subasta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subasta ALTER COLUMN id_subasta SET DEFAULT nextval('public.subasta_id_subasta_seq'::regclass);


--
-- TOC entry 2124 (class 2604 OID 16521)
-- Name: tipo_lote id_tipo_lote; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_lote ALTER COLUMN id_tipo_lote SET DEFAULT nextval('public.tipo_lote_id_tipo_lote_seq'::regclass);


--
-- TOC entry 2125 (class 2604 OID 16522)
-- Name: usuario id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuario_id_usuario_seq'::regclass);


--
-- TOC entry 2126 (class 2604 OID 16523)
-- Name: usuario_suscripcion id_usuario_suscripcion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_suscripcion ALTER COLUMN id_usuario_suscripcion SET DEFAULT nextval('public.usuario_suscripcion_id_usuario_suscripcion_seq'::regclass);


--
-- TOC entry 2292 (class 0 OID 16386)
-- Dependencies: 185
-- Data for Name: departamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.departamento (id_departamento, isocode, nombre, zonesv_id) FROM stdin;
1	SV-AH	Ahuachapán	1
2	SV-SA	Santa Ana	1
3	SV-SO	Sonsonate	1
4	SV-LI	La Libertad	2
5	SV-CH	Chalatenango	2
6	SV-SS	San Salvador	2
7	SV-CU	Cuscatlán	3
8	SV-PA	La Paz	3
9	SV-CA	Cabañas	3
10	SV-SV	San Vicente	3
11	SV-US	Usulután	4
12	SV-MO	Morazán	4
13	SV-SM	San Miguel	4
14	SV-UN	La Unión	4
\.


--
-- TOC entry 2294 (class 0 OID 16394)
-- Dependencies: 187
-- Data for Name: estado_lote; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estado_lote (id_estado_lote, activo, descripcion, nombre) FROM stdin;
1	1	Estado para los lotes que estan proximos a subastarse	Proximo
2	1	Estado para los lotes que ya fueron subastados	Subastados
\.


--
-- TOC entry 2296 (class 0 OID 16402)
-- Dependencies: 189
-- Data for Name: estado_sub; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estado_sub (id_estado_sub, activo, descripcion, nombre) FROM stdin;
3	1	Estado para las suscripciones con reserva	Reserva
4	1	Estado para las suscripciones solo de visualizacion primero	Ver primero
\.


--
-- TOC entry 2298 (class 0 OID 16410)
-- Dependencies: 191
-- Data for Name: estado_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estado_usuario (id_estado_usuario, activo, descripcion, nombre) FROM stdin;
1	1	Estado para los usuarios que aun no han sido verificados	NO VERIFICADO
2	1	Estados para los usuarios que ya fueron verificados	VERIFICADO
\.


--
-- TOC entry 2300 (class 0 OID 16418)
-- Dependencies: 193
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.image (id_image, orden, src, id_lote) FROM stdin;
1	1	laptop1.jpg	1
2	2	laptop2.jpg	1
3	3	laptop3.jpg	1
4	2	nissan_versa-2011-2.jpg	7
5	3	nissan_versa-2011-3.jpg	7
6	1	nissan_versa-2011-1.jpg	7
7	1	escritorio1.jpg	8
8	2	escritorio2.jpg	8
9	3	escritorio3.jpg	8
10	1	1nissan_versa-2011-1.jpg	10
12	2	2nissan_versa-2011-2.jpg	10
13	1	2escritorio2.jpg	11
14	3	3escritorio3.jpg	11
15	2	1escritorio1.jpg	11
11	3	3nissan_versa-2011-3.jpg	10
16	1	1638461449461.jpeg	14
17	2	1638461449498.jpeg	14
18	3	1638461449514.jpeg	14
19	1	b0a78b3cd6c3423eb302fc0044a54301.jpeg	15
20	2	37e73a34b491474fa494ed3b544c4bcc.jpeg	15
21	3	5e062b6f3ef24b7ba4f055cfcc494892.jpeg	15
22	1	d3603b0c6cda440692e7ae5401d52339.jpeg	16
23	2	0b92f9d27bd0473a9644ba2e0f4f0ebb.jpeg	16
24	3	96738d6972e04fc191b6b39cef359279.jpeg	16
25	1	8f8f8cd348f248e4bab3c7f20358946d.png	19
26	2	99f6127209534f66a71c050224fd559a.jpeg	19
27	3	f56316ae49634ba09f7c8312ce87fe47.jpeg	19
28	1	922e5ef321e54711b6b3bf7ef1a554f4.jpeg	20
29	1	22c3de63650b41a383e884ecc5926ea0.png	24
\.


--
-- TOC entry 2302 (class 0 OID 16423)
-- Dependencies: 195
-- Data for Name: lote; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lote (id_lote, descripcion, impuestos, medidas, nombre, peso, precio, id_estado_lote, id_subasta, id_tipo_lote, fecha_fin, fecha_inicio, hora_fin, hora_inicio, id_ganador, fecha_ganador, delete, porcentaje_reserva) FROM stdin;
10	Descripcion del lote para nissan azul	\N	1.2m	Nissan azul	1T	6000.00	1	3	2	2021-11-26 00:00:00	2021-11-24 00:00:00	2021-11-17 20:30:00	2021-11-17 09:00:00	\N	\N	0	25.00
7	Carro nissan gris	\N	1.2m	carro nissan	324	676.00	1	3	2	2021-11-26 00:00:00	2021-11-24 00:00:00	2021-11-17 20:30:00	2021-11-17 09:00:00	\N	\N	0	25.00
11	descripcion del segundo lote de escritorio	\N	1.2m	segundo lote de escritorio	24kg	300.00	1	7	2	2021-11-26 00:00:00	2021-11-23 00:00:00	2021-11-18 18:30:00	2021-11-25 08:00:00	\N	\N	0	30.00
8	Descripcion del lote 	\N	25	escritorio	32	400.00	1	7	2	2021-11-26 00:00:00	2021-11-23 00:00:00	2021-11-18 18:30:00	2021-11-25 08:00:00	\N	\N	0	0.00
1	asus i5	2000	25cm x 25cm	Asus i5	45kg	500.00	1	7	1	2021-11-26 00:00:00	2021-11-23 00:00:00	2021-11-18 18:30:00	2021-11-25 08:00:00	\N	\N	0	25.00
14	Primer lote de la subasta de pickups para prueba de subir imagenes en base 64	\N	1.2m	NIssan Frontier	1Tn	8000.00	1	10	2	2021-12-04 06:00:00	2021-12-03 06:00:00	2021-12-03 01:00:00	2021-12-02 13:00:00	\N	\N	0	27.00
15	Descripcion del toyota hilux	\N	1.2m	Toyota hilux	1tn	9000.00	1	10	2	2021-12-04 06:00:00	2021-12-03 06:00:00	2021-12-03 01:00:00	2021-12-02 13:00:00	\N	\N	0	27.00
16	Descripon del lote para nissan np300	\N	1.2m	Carro nissan np300	1tn	10000.00	1	10	2	2022-02-28 06:00:00	2022-02-28 06:00:00	2022-03-01 01:00:00	0202-12-02 13:00:00	\N	\N	0	27.00
19	tratamiento de covid	\N	1	Asprina	5	100.00	1	11	2	2022-02-22 00:00:00	2022-02-15 00:00:00	1970-01-01 18:00:00	1970-01-01 14:00:00	\N	\N	0	26.00
20	paquete de medicinas.	\N	20	OMEPRAZOL	14	500.00	1	11	1	2022-02-22 06:00:00	2022-02-15 06:00:00	2022-02-14 18:00:00	2022-02-14 14:00:00	\N	\N	0	15.00
24	lote oo1	\N	100 cm	lote 001	12 kg	50.00	1	3	2	2021-11-26 00:00:00	2021-11-24 00:00:00	1970-01-01 20:30:00	1970-01-01 09:00:00	\N	\N	0	20.00
\.


--
-- TOC entry 2304 (class 0 OID 16431)
-- Dependencies: 197
-- Data for Name: lote_participantes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lote_participantes (id_lote_participantes, id_usuario, paso, id_lote) FROM stdin;
22	13	\N	1
23	13	\N	11
24	13	\N	8
25	13	1	1
19	21	\N	1
20	21	\N	8
21	21	\N	11
26	13	1	7
27	1	1	10
28	1	1	10
29	1	1	10
30	1	1	10
31	1	1	10
32	13	1	7
33	1	1	10
34	1	1	10
35	1	1	10
36	1	1	10
37	1	1	10
38	24	1	7
39	24	1	8
40	1	1	10
41	1	1	10
42	1	1	10
43	13	1	7
44	13	1	7
45	13	1	7
46	1	1	16
47	1	1	11
48	1	1	16
49	24	1	16
50	24	1	10
51	24	1	10
52	24	1	10
53	24	1	24
\.


--
-- TOC entry 2306 (class 0 OID 16436)
-- Dependencies: 199
-- Data for Name: municipio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.municipio (id_municipio, nombre, id_departamento) FROM stdin;
1	Ahuachapán	1
2	Jujutla	1
3	Atiquizaya	1
4	Concepción de Ataco	1
5	El Refugio	1
6	Guaymango	1
7	Apaneca	1
8	San Francisco Menéndez	1
9	San Lorenzo	1
10	San Pedro Puxtla	1
11	Tacuba	1
12	Turín	1
13	Candelaria de la Frontera	2
14	Chalchuapa	2
15	Coatepeque	2
16	El Congo	2
17	El Porvenir	2
18	Masahuat	2
19	Metapán	2
20	San Antonio Pajonal	2
21	San Sebastián Salitrillo	2
22	Santa Ana	2
23	Santa Rosa Guachipilín	2
24	Santiago de la Frontera	2
25	Texistepeque	2
26	Acajutla	3
27	Armenia	3
28	Caluco	3
29	Cuisnahuat	3
30	Izalco	3
31	Juayúa	3
32	Nahuizalco	3
33	Nahulingo	3
34	Salcoatitán	3
35	San Antonio del Monte	3
36	San Julián	3
37	Santa Catarina Masahuat	3
38	Santa Isabel Ishuatán	3
39	Santo Domingo de Guzmán	3
40	Sonsonate	3
41	Sonzacate	3
42	Alegría	11
43	Berlín	11
44	California	11
45	Concepción Batres	11
46	El Triunfo	11
47	Ereguayquín	11
48	Estanzuelas	11
49	Jiquilisco	11
50	Jucuapa	11
51	Jucuarán	11
52	Mercedes Umaña	11
53	Nueva Granada	11
54	Ozatlán	11
55	Puerto El Triunfo	11
56	San Agustín	11
57	San Buenaventura	11
58	San Dionisio	11
59	San Francisco Javier	11
60	Santa Elena	11
61	Santa María	11
62	Santiago de María	11
63	Tecapán	11
64	Usulután	11
65	Carolina	13
66	Chapeltique	13
67	Chinameca	13
68	Chirilagua	13
69	Ciudad Barrios	13
70	Comacarán	13
71	El Tránsito	13
72	Lolotique	13
73	Moncagua	13
74	Nueva Guadalupe	13
75	Nuevo Edén de San Juan	13
76	Quelepa	13
77	San Antonio del Mosco	13
78	San Gerardo	13
79	San Jorge	13
80	San Luis de la Reina	13
81	San Miguel	13
82	San Rafael Oriente	13
83	Sesori	13
84	Uluazapa	13
85	Arambala	12
86	Cacaopera	12
87	Chilanga	12
88	Corinto	12
89	Delicias de Concepción	12
90	El Divisadero	12
91	El Rosario (Morazán)	12
92	Gualococti	12
93	Guatajiagua	12
94	Joateca	12
95	Jocoaitique	12
96	Jocoro	12
97	Lolotiquillo	12
98	Meanguera	12
99	Osicala	12
100	Perquín	12
101	San Carlos	12
102	San Fernando (Morazán)	12
103	San Francisco Gotera	12
104	San Isidro (Morazán)	12
105	San Simón	12
106	Sensembra	12
107	Sociedad	12
108	Torola	12
109	Yamabal	12
110	Yoloaiquín	12
111	La Unión	14
112	San Alejo	14
113	Yucuaiquín	14
114	Conchagua	14
115	Intipucá	14
116	San José	14
117	El Carmen (La Unión)	14
118	Yayantique	14
119	Bolívar	14
120	Meanguera del Golfo	14
121	Santa Rosa de Lima	14
122	Pasaquina	14
123	Anamoros	14
124	Nueva Esparta	14
125	El Sauce	14
126	Concepción de Oriente	14
127	Polorós	14
128	Lislique	14
129	Antiguo Cuscatlán	4
130	Chiltiupán	4
131	Ciudad Arce	4
132	Colón	4
133	Comasagua	4
134	Huizúcar	4
135	Jayaque	4
136	Jicalapa	4
137	La Libertad	4
138	Santa Tecla	4
139	Nuevo Cuscatlán	4
140	San Juan Opico	4
141	Quezaltepeque	4
142	Sacacoyo	4
143	San José Villanueva	4
144	San Matías	4
145	San Pablo Tacachico	4
146	Talnique	4
147	Tamanique	4
148	Teotepeque	4
149	Tepecoyo	4
150	Zaragoza	4
151	Agua Caliente	5
152	Arcatao	5
153	Azacualpa	5
154	Cancasque	5
155	Chalatenango	5
156	Citalá	5
157	Comapala	5
158	Concepción Quezaltepeque	5
159	Dulce Nombre de María	5
160	El Carrizal	5
161	El Paraíso	5
162	La Laguna	5
163	La Palma	5
164	La Reina	5
165	Las Vueltas	5
166	Nueva Concepción	5
167	Nueva Trinidad	5
168	Nombre de Jesús	5
169	Ojos de Agua	5
170	Potonico	5
171	San Antonio de la Cruz	5
172	San Antonio Los Ranchos	5
173	San Fernando (Chalatenango)	5
174	San Francisco Lempa	5
175	San Francisco Morazán	5
176	San Ignacio	5
177	San Isidro Labrador	5
178	Las Flores	5
179	San Luis del Carmen	5
180	San Miguel de Mercedes	5
181	San Rafael	5
182	Santa Rita	5
183	Tejutla	5
184	Cojutepeque	7
185	Candelaria	7
186	El Carmen (Cuscatlán)	7
187	El Rosario (Cuscatlán)	7
188	Monte San Juan	7
189	Oratorio de Concepción	7
190	San Bartolomé Perulapía	7
191	San Cristóbal	7
192	San José Guayabal	7
193	San Pedro Perulapán	7
194	San Rafael Cedros	7
195	San Ramón	7
196	Santa Cruz Analquito	7
197	Santa Cruz Michapa	7
198	Suchitoto	7
199	Tenancingo	7
200	Aguilares	6
201	Apopa	6
202	Ayutuxtepeque	6
203	Cuscatancingo	6
204	Ciudad Delgado	6
205	El Paisnal	6
206	Guazapa	6
207	Ilopango	6
208	Mejicanos	6
209	Nejapa	6
210	Panchimalco	6
211	Rosario de Mora	6
212	San Marcos	6
213	San Martín	6
214	San Salvador	6
215	Santiago Texacuangos	6
216	Santo Tomás	6
217	Soyapango	6
218	Tonacatepeque	6
219	Zacatecoluca	8
220	Cuyultitán	8
221	El Rosario (La Paz)	8
222	Jerusalén	8
223	Mercedes La Ceiba	8
224	Olocuilta	8
225	Paraíso de Osorio	8
226	San Antonio Masahuat	8
227	San Emigdio	8
228	San Francisco Chinameca	8
229	San Pedro Masahuat	8
230	San Juan Nonualco	8
231	San Juan Talpa	8
232	San Juan Tepezontes	8
233	San Luis La Herradura	8
234	San Luis Talpa	8
235	San Miguel Tepezontes	8
236	San Pedro Nonualco	8
237	San Rafael Obrajuelo	8
238	Santa María Ostuma	8
239	Santiago Nonualco	8
240	Tapalhuaca	8
241	Cinquera	9
242	Dolores	9
243	Guacotecti	9
244	Ilobasco	9
245	Jutiapa	9
246	San Isidro (Cabañas)	9
247	Sensuntepeque	9
248	Tejutepeque	9
249	Victoria	9
250	Apastepeque	10
251	Guadalupe	10
252	San Cayetano Istepeque	10
253	San Esteban Catarina	10
254	San Ildefonso	10
255	San Lorenzo	10
256	San Sebastián	10
257	San Vicente	10
258	Santa Clara	10
259	Santo Domingo	10
260	Tecoluca	10
261	Tepetitán	10
262	Verapaz	10
\.


--
-- TOC entry 2308 (class 0 OID 16441)
-- Dependencies: 201
-- Data for Name: parametro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.parametro (id_estado_parametro, activo, descripcion, nombre, valor) FROM stdin;
1	1	Parametro para el porcentaje que se debe pagar en la reserva	porcentaje	26
\.


--
-- TOC entry 2310 (class 0 OID 16449)
-- Dependencies: 203
-- Data for Name: permiso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permiso (id_permiso, activo, descripcion, nombre) FROM stdin;
\.


--
-- TOC entry 2312 (class 0 OID 16457)
-- Dependencies: 205
-- Data for Name: puja; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.puja (id_puja, fecha, monto, id_lote, id_usuario) FROM stdin;
15	2021-11-25 10:42:57.644	505.00	1	4
16	2021-11-25 10:43:09.819	506.00	1	13
17	2021-12-02 17:03:11.755	600.00	1	21
18	2021-12-02 17:03:30.22	700.00	1	21
19	2021-12-02 20:31:12.624	750.00	1	21
\.


--
-- TOC entry 2314 (class 0 OID 16462)
-- Dependencies: 207
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rol (id_rol, activo, descripcion, nombre) FROM stdin;
1	1	Rol para los usuarios administradores	ADMIN
2	1	Rol para los usuarios que haran ofertas	USUARIO
3	1	Rol para los usuarios tesoreros	TESORERO
\.


--
-- TOC entry 2316 (class 0 OID 16470)
-- Dependencies: 209
-- Data for Name: rol_permiso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rol_permiso (id_rol_permiso, id_permiso, id_rol) FROM stdin;
\.


--
-- TOC entry 2318 (class 0 OID 16475)
-- Dependencies: 211
-- Data for Name: subasta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subasta (id_subasta, aumento, fecha_fin_suscripcion, fecha_inicio, fecha_inicio_suscripcion, hora_fin, hora_inicio, precio_inicio, fecha_fin, hora_defecto, descripcion, nombre, hora_inicio_suscripcion, hora_fin_suscripcion, activo, delete) FROM stdin;
2	\N	2021-11-18 00:00:00	2021-11-19 00:00:00	2021-11-17 00:00:00	2021-11-17 20:00:00	2021-11-17 07:00:00	\N	2021-11-20 00:00:00	2021-11-17 09:00:00	Subasta de pruebas desde la pagina	Subasta prueba	2021-11-17 08:00:00	2021-11-17 17:00:00	0	1
1	\N	2021-11-18 00:00:00	2021-11-19 00:00:00	2021-11-17 00:00:00	2021-11-17 15:00:00	2021-11-17 10:00:00	\N	2021-11-20 00:00:00	2021-11-17 11:00:00	Descripcion	Lote de computadoras	2021-11-17 06:00:00	2021-11-17 21:00:00	0	1
8	\N	2021-11-30 00:00:00	2021-12-01 00:00:00	2021-11-23 00:00:00	2021-11-22 20:30:00	2021-11-22 05:30:00	\N	2021-12-02 00:00:00	2021-11-22 10:30:00	subasta para probar la nueva api	subasta con nueva api	2021-11-22 01:00:00	2021-11-22 20:30:00	0	1
10	\N	2021-12-02 06:00:00	2021-12-03 06:00:00	2021-12-01 06:00:00	2021-12-03 01:00:00	2021-12-02 13:00:00	\N	2021-12-04 06:00:00	2021-12-02 13:00:00	Subasta de prueba para los lotes de pickups	Subasta de pick ups	2021-12-02 07:00:00	2021-12-03 04:30:00	1	0
3	\N	2021-11-22 00:00:00	2021-11-24 00:00:00	2021-11-19 00:00:00	2021-11-17 20:30:00	2021-11-25 08:00:00	\N	2021-11-26 00:00:00	2021-11-17 09:00:00	Subasta para vender los carros	Subasta de carros	2021-11-17 06:00:00	2021-11-17 18:30:00	1	0
7	\N	2021-11-26 00:00:00	2021-11-23 00:00:00	2021-11-17 00:00:00	2021-11-18 18:30:00	2021-11-25 07:00:00	\N	2021-11-26 00:00:00	2021-11-25 08:00:00	Subasta con tiempos en la prueba del prototipo	Subasta prototipo	2021-11-18 08:30:00	2021-11-18 16:30:00	1	0
9	\N	2021-11-30 06:00:00	2021-12-02 06:00:00	\N	2021-12-02 01:00:00	2021-12-01 13:00:00	\N	2021-12-02 06:00:00	2021-12-01 14:00:00	Subasta de pickups para el 2 de diciembre	Subasta de pickups	2021-12-01 14:00:00	2021-12-02 02:00:00	0	1
11	\N	2022-02-15 06:00:00	2022-02-15 06:00:00	2022-02-14 06:00:00	2022-02-14 18:00:00	2022-02-14 14:00:00	\N	2022-02-22 06:00:00	2022-02-14 14:00:00	paquete covid	Medicamentos	2022-02-14 18:00:00	2022-02-14 13:00:00	1	0
\.


--
-- TOC entry 2320 (class 0 OID 16483)
-- Dependencies: 213
-- Data for Name: tipo_lote; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_lote (id_tipo_lote, activo, descripcion, nombre) FROM stdin;
2	1	Tipo de lote no perecedero	Producto no perecedero
1	1	Tipo de lote perecedero	Producto perecedero
\.


--
-- TOC entry 2322 (class 0 OID 16491)
-- Dependencies: 215
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id_usuario, codigo, correo, departamento, direccion, dui, municipio, nit, nombre, password, telefono, username, id_estado_usuario, id_rol, token) FROM stdin;
13	12345	correo@subastas.com	San Salvador	San Salvador	04125236-6	San Salvador	0210-260697-102-2	Kevin	$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG	76543210	prueba	2	2	non-token
9	no-cod	rcarlosdeb@gmail.com	San Salvador	San Salvador, San Salvador	01473106-7	San Salvador	0120-120381-105-1	rcarlosdeb		76543210	nuevo	1	2	be9779d0-cbda-461a-9f36-8f5651ee79d1
28	53361	juliolandaverde@gmail.com	La Libertad	san salvador	111	Chiltiupán	1	Walter Arturo	$2a$10$8eCLKDOzoH3xy0UJBPnsMe1GvaFBbMfdy93K5xmr2.nUhLXO9bHJG	777777777	nuevo	2	2	non-token
15	no-cod	andrade209701@gmail.com	san salvador	direccion de prueba	1234124	san salvador	12341234234	steven		12341234	nuevo	1	2	57ccb7e5-97e2-4909-a267-1f2ef69f0776
16	no-cod	andrade20970@gmail.com	San Salvador	San Salvador, San Salvador	01473106	San Salvador	0120-120381105-1	Steven Andrade		76859025	nuevo	1	2	b5d8c478-5f33-41db-9337-beb08eca6539
17	no-cod	andrade209@gmail.com	san salvador	direccion	12341234	san salvador	12341234	steven		12341234	nuevo	1	2	7af15911-2b94-42dc-bc0b-86b067213841
18	no-cod	andrade2097@gmail.com	san salvador	direccion	12341234	san salvador	12341234	steven		12341234	nuevo	1	2	e225969a-9ae5-426c-9e31-3f9a82ecc4dc
19	no-cod	steven209701@hotmail.com	san salvador	Ilopango San Salvador	1243123	san salvador	12341234	steven andrade		12341234	nuevo	1	2	25ab18c6-e34f-45a9-9689-9448bcbce71f
24	23436	josenasser@latmobile.com	San Salvador	San Salvador, San Salvador	05969419-9	San Salvador	0614-101199-118-6	Jose Nasser	$2a$10$eP157lYGWpuNTiETjx6jwu/HzizOQmnOqjiqdA8hIJullXJITLXq2	79073070	nuevo	2	2	non-token
1	65743	julio@latmobile.com	San Salvador	San Salvador, San Salvador	01473106-7	San Salvador	0120-120381-105-1	Julio Landaverde	$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG	76543210	JulioLandaverde	2	1	884c3cff-ceaa-44d7-bd23-2cf4cd04016e
21	57653	alex@latmobile.com	San Salvador	San Salvador	05544557-4	Ciudad Delgado	0210-240597-104-2	Alex	$2a$10$PieuKQ06XqoFpJh/nP06XeC0/D.Bx5UC3Cqpqncfzapnczd1KWx5u	76543219	nuevo	2	2	non-token
14	40768	steven@latmobile.com	San Salvador	San Salvador, San Salvador	01473106-7	San Salvador	0120-120381-105-1	Steven	$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG	76543210	nuevo	1	2	non-token
22	no-cod	prueba@gmail.com	San Salvador	prueba	12341243	San Salvador	12431234	prueba		12341234	nuevo	1	2	39489431-eb21-4cf5-b359-c9f49f8801fe
4	27677	rcarlos.rc31@gmail.com	San Salvador	San Salvador	26593718-3	San Salvador	0340-140281-301-3	rcarlos31	$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG	76543210	josecarlos	2	2	non-token
3	32886	carlos@latmobile.com	Santa Ana	Santa Ana	16593718-3	Santa Ana	0340-140281-301-3	carlos	$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG	76543210	carlos	2	1	non-token
25	63131	rafael@latmobile.com	Santa Ana	Santa Ana, Santa Ana	05251462-3	Santa Ana	0614-141095-113-9	Rafael Guevara	$2a$10$UKnK8L7z/w/U9b25pAgapuYzeTHQ8/595PgmdJ2ClkTPwoGua2DN6	70235141	nuevo	2	2	non-token
26	no-cod	jr611it@latmobile.com	San Salvador	San Salvador, San Salvador	05251462-3	San Salvador	0614-141095-113-9	Jonathan Rivera		70235141	nuevo	1	2	14ac29cb-f36e-49a3-8f41-89e6e5322c22
23	no-cod	rtert	Ahuachapán	rtr	rtret	Atiquizaya	rtretret	rr		rtert	nuevo	1	2	9eb7c3e2-00ba-43d7-a977-61b9f4b4513f
27	30717	jr611it@gmail.com	San Salvador	San Salvador, San Salvador	05251462-3	San Salvador	0614-141095-113-9	Jonathan Rivera	$2a$10$ey0HiJEj9RnAXBb5ED/l0enqRdyTia4TaQ7.eMr1WLZD.yF1WPTce	70235141	nuevo	2	2	non-token
\.


--
-- TOC entry 2324 (class 0 OID 16499)
-- Dependencies: 217
-- Data for Name: usuario_suscripcion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_suscripcion (id_usuario_suscripcion, banco, fecha_adjudicacion, identificacion_tansaccion, monto_maximo_puja, porcentaje_reserva, reserva, transaccion, id_estado_sub, id_usuario, id_lote, comprobante, valid) FROM stdin;
8		\N	\N	\N	0.00	0.00		3	4	7	undefined	0
9	Banco Agricola	\N	\N	\N	25.00	125.00	0555	3	13	1	icono.png	0
6	Banco Agricola	\N	\N	\N	20.00	100.00	01112	3	21	1	avatar3.png	1
10	Banco Agricola	\N	\N	\N	25.00	101.00	100	3	13	7	stanley_oyod.jpg	0
11	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1643991498462.jpeg	0
12	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1643991765763.jpeg	0
13	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1643991784376.jpeg	0
14	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1643992087182.jpeg	0
15	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1643992095803.jpeg	0
16	Banco Agricola	\N	\N	\N	25.00	101.00	100	3	13	7	1643994282665.jpeg	0
17	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1644250093105.jpeg	0
18	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1644250532497.jpeg	0
19	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1644250541927.jpeg	0
20	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1644251523153.jpeg	0
21	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1644251666085.jpeg	0
22	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	24	7	1644262328314.jpeg	0
23	Banco Agricola	\N	\N	\N	0.00	101.00	504	3	24	8	1644262398768.jpeg	0
24	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1644263253578.jpeg	0
25	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1644263505347.jpeg	0
26	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	1	10	1644289608999.jpeg	0
27	Banco Agricola	\N	\N	\N	25.00	101.00	100	3	13	7	1644365813717.jpeg	0
28	Banco Agricola	\N	\N	\N	25.00	101.00	100	3	13	7	1644365847730.jpeg	0
29	Banco Agricola	\N	\N	\N	25.00	101.00	100	3	13	7	1644853331241.jpeg	0
30	Banco Agricola	\N	\N	\N	27.00	101.00	504	3	1	16	1644853627097.jpeg	0
31	Banco Agricola	\N	\N	\N	30.00	26.00	505	3	1	11	1644856495525.jpeg	0
32	Banco Agricola	\N	\N	\N	27.00	101.00	504	3	1	16	1644872655373.jpeg	0
33	Banco Agricola	\N	\N	\N	27.00	101.00	504	3	24	16	1644872695728.jpeg	0
34	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	24	10	1644968004002.jpeg	0
35	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	24	10	1644968206728.jpeg	0
36	Banco Agricola	\N	\N	\N	25.00	101.00	504	3	24	10	1644968379782.jpeg	0
37	Banco Agricola	\N	\N	\N	20.00	101.00	504	3	24	24	1645043140919.jpeg	0
\.


--
-- TOC entry 2349 (class 0 OID 0)
-- Dependencies: 186
-- Name: departamento_id_departamento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.departamento_id_departamento_seq', 14, true);


--
-- TOC entry 2350 (class 0 OID 0)
-- Dependencies: 188
-- Name: estado_lote_id_estado_lote_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estado_lote_id_estado_lote_seq', 1, false);


--
-- TOC entry 2351 (class 0 OID 0)
-- Dependencies: 190
-- Name: estado_sub_id_estado_sub_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estado_sub_id_estado_sub_seq', 5, true);


--
-- TOC entry 2352 (class 0 OID 0)
-- Dependencies: 192
-- Name: estado_usuario_id_estado_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estado_usuario_id_estado_usuario_seq', 1, false);


--
-- TOC entry 2353 (class 0 OID 0)
-- Dependencies: 194
-- Name: image_id_image_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_id_image_seq', 29, true);


--
-- TOC entry 2354 (class 0 OID 0)
-- Dependencies: 196
-- Name: lote_id_lote_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lote_id_lote_seq', 24, true);


--
-- TOC entry 2355 (class 0 OID 0)
-- Dependencies: 198
-- Name: lote_participantes_id_lote_participantes_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lote_participantes_id_lote_participantes_seq', 53, true);


--
-- TOC entry 2356 (class 0 OID 0)
-- Dependencies: 200
-- Name: municipio_id_municipio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.municipio_id_municipio_seq', 262, true);


--
-- TOC entry 2357 (class 0 OID 0)
-- Dependencies: 202
-- Name: parametro_id_estado_parametro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.parametro_id_estado_parametro_seq', 1, false);


--
-- TOC entry 2358 (class 0 OID 0)
-- Dependencies: 204
-- Name: permiso_id_permiso_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.permiso_id_permiso_seq', 1, false);


--
-- TOC entry 2359 (class 0 OID 0)
-- Dependencies: 206
-- Name: puja_id_puja_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.puja_id_puja_seq', 19, true);


--
-- TOC entry 2360 (class 0 OID 0)
-- Dependencies: 208
-- Name: rol_id_rol_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rol_id_rol_seq', 1, false);


--
-- TOC entry 2361 (class 0 OID 0)
-- Dependencies: 210
-- Name: rol_permiso_id_rol_permiso_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rol_permiso_id_rol_permiso_seq', 1, false);


--
-- TOC entry 2362 (class 0 OID 0)
-- Dependencies: 212
-- Name: subasta_id_subasta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subasta_id_subasta_seq', 11, true);


--
-- TOC entry 2363 (class 0 OID 0)
-- Dependencies: 214
-- Name: tipo_lote_id_tipo_lote_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_lote_id_tipo_lote_seq', 2, true);


--
-- TOC entry 2364 (class 0 OID 0)
-- Dependencies: 216
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_usuario_seq', 28, true);


--
-- TOC entry 2365 (class 0 OID 0)
-- Dependencies: 218
-- Name: usuario_suscripcion_id_usuario_suscripcion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_suscripcion_id_usuario_suscripcion_seq', 37, true);


--
-- TOC entry 2128 (class 2606 OID 16525)
-- Name: departamento departamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departamento
    ADD CONSTRAINT departamento_pkey PRIMARY KEY (id_departamento);


--
-- TOC entry 2130 (class 2606 OID 16527)
-- Name: estado_lote estado_lote_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_lote
    ADD CONSTRAINT estado_lote_pkey PRIMARY KEY (id_estado_lote);


--
-- TOC entry 2132 (class 2606 OID 16529)
-- Name: estado_sub estado_sub_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_sub
    ADD CONSTRAINT estado_sub_pkey PRIMARY KEY (id_estado_sub);


--
-- TOC entry 2134 (class 2606 OID 16531)
-- Name: estado_usuario estado_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_usuario
    ADD CONSTRAINT estado_usuario_pkey PRIMARY KEY (id_estado_usuario);


--
-- TOC entry 2136 (class 2606 OID 16533)
-- Name: image image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id_image);


--
-- TOC entry 2140 (class 2606 OID 16535)
-- Name: lote_participantes lote_participantes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote_participantes
    ADD CONSTRAINT lote_participantes_pkey PRIMARY KEY (id_lote_participantes);


--
-- TOC entry 2138 (class 2606 OID 16537)
-- Name: lote lote_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote
    ADD CONSTRAINT lote_pkey PRIMARY KEY (id_lote);


--
-- TOC entry 2142 (class 2606 OID 16539)
-- Name: municipio municipio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.municipio
    ADD CONSTRAINT municipio_pkey PRIMARY KEY (id_municipio);


--
-- TOC entry 2144 (class 2606 OID 16541)
-- Name: parametro parametro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parametro
    ADD CONSTRAINT parametro_pkey PRIMARY KEY (id_estado_parametro);


--
-- TOC entry 2146 (class 2606 OID 16543)
-- Name: permiso permiso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permiso
    ADD CONSTRAINT permiso_pkey PRIMARY KEY (id_permiso);


--
-- TOC entry 2148 (class 2606 OID 16545)
-- Name: puja puja_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.puja
    ADD CONSTRAINT puja_pkey PRIMARY KEY (id_puja);


--
-- TOC entry 2152 (class 2606 OID 16547)
-- Name: rol_permiso rol_permiso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol_permiso
    ADD CONSTRAINT rol_permiso_pkey PRIMARY KEY (id_rol_permiso);


--
-- TOC entry 2150 (class 2606 OID 16549)
-- Name: rol rol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (id_rol);


--
-- TOC entry 2154 (class 2606 OID 16551)
-- Name: subasta subasta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subasta
    ADD CONSTRAINT subasta_pkey PRIMARY KEY (id_subasta);


--
-- TOC entry 2156 (class 2606 OID 16553)
-- Name: tipo_lote tipo_lote_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_lote
    ADD CONSTRAINT tipo_lote_pkey PRIMARY KEY (id_tipo_lote);


--
-- TOC entry 2158 (class 2606 OID 16555)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 2160 (class 2606 OID 16557)
-- Name: usuario_suscripcion usuario_suscripcion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_suscripcion
    ADD CONSTRAINT usuario_suscripcion_pkey PRIMARY KEY (id_usuario_suscripcion);


--
-- TOC entry 2167 (class 2606 OID 16558)
-- Name: puja fk3rxt4cv5ieoqkmi1lldnwfpy8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.puja
    ADD CONSTRAINT fk3rxt4cv5ieoqkmi1lldnwfpy8 FOREIGN KEY (id_lote) REFERENCES public.lote(id_lote);


--
-- TOC entry 2173 (class 2606 OID 16563)
-- Name: usuario_suscripcion fk7jqn8nnr2u7d6is7ramjinqxf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_suscripcion
    ADD CONSTRAINT fk7jqn8nnr2u7d6is7ramjinqxf FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 2165 (class 2606 OID 16568)
-- Name: lote_participantes fk8inyiwc0wts59e7y8ggdur20i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote_participantes
    ADD CONSTRAINT fk8inyiwc0wts59e7y8ggdur20i FOREIGN KEY (id_lote) REFERENCES public.lote(id_lote);


--
-- TOC entry 2161 (class 2606 OID 16573)
-- Name: image fk8khkdtx12unx6kv3jfkgx324w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT fk8khkdtx12unx6kv3jfkgx324w FOREIGN KEY (id_lote) REFERENCES public.lote(id_lote);


--
-- TOC entry 2174 (class 2606 OID 16578)
-- Name: usuario_suscripcion fk9rxlixtnqafkbkyys1kc6sqa7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_suscripcion
    ADD CONSTRAINT fk9rxlixtnqafkbkyys1kc6sqa7 FOREIGN KEY (id_estado_sub) REFERENCES public.estado_sub(id_estado_sub);


--
-- TOC entry 2168 (class 2606 OID 16583)
-- Name: puja fkax904dgs3yq5f0o37o26m0vy7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.puja
    ADD CONSTRAINT fkax904dgs3yq5f0o37o26m0vy7 FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 2162 (class 2606 OID 16588)
-- Name: lote fkdj4bkvjhe4mq8nvfdm16b2uki; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote
    ADD CONSTRAINT fkdj4bkvjhe4mq8nvfdm16b2uki FOREIGN KEY (id_estado_lote) REFERENCES public.estado_lote(id_estado_lote);


--
-- TOC entry 2166 (class 2606 OID 16593)
-- Name: municipio fke1way3pa23l5j480h48x5tp65; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.municipio
    ADD CONSTRAINT fke1way3pa23l5j480h48x5tp65 FOREIGN KEY (id_departamento) REFERENCES public.departamento(id_departamento);


--
-- TOC entry 2163 (class 2606 OID 16598)
-- Name: lote fkiol4glm70mfg3cpf9fpcbwc17; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote
    ADD CONSTRAINT fkiol4glm70mfg3cpf9fpcbwc17 FOREIGN KEY (id_tipo_lote) REFERENCES public.tipo_lote(id_tipo_lote);


--
-- TOC entry 2171 (class 2606 OID 16603)
-- Name: usuario fkmyv3138vvci6kaq3y5kt4cntu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fkmyv3138vvci6kaq3y5kt4cntu FOREIGN KEY (id_rol) REFERENCES public.rol(id_rol);


--
-- TOC entry 2164 (class 2606 OID 16608)
-- Name: lote fknqqfgjgk985p90wqp5c3imil3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote
    ADD CONSTRAINT fknqqfgjgk985p90wqp5c3imil3 FOREIGN KEY (id_subasta) REFERENCES public.subasta(id_subasta);


--
-- TOC entry 2172 (class 2606 OID 16613)
-- Name: usuario fkpp7wh5kxirm89ajdtpi5c3lwt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fkpp7wh5kxirm89ajdtpi5c3lwt FOREIGN KEY (id_estado_usuario) REFERENCES public.estado_usuario(id_estado_usuario);


--
-- TOC entry 2169 (class 2606 OID 16618)
-- Name: rol_permiso fkrhxhgw05bdvokfrpppumlfh5d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol_permiso
    ADD CONSTRAINT fkrhxhgw05bdvokfrpppumlfh5d FOREIGN KEY (id_permiso) REFERENCES public.permiso(id_permiso);


--
-- TOC entry 2170 (class 2606 OID 16623)
-- Name: rol_permiso fksxc3d8lmtj7em6n8j0wl4jwco; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol_permiso
    ADD CONSTRAINT fksxc3d8lmtj7em6n8j0wl4jwco FOREIGN KEY (id_rol) REFERENCES public.rol(id_rol);


-- Completed on 2022-02-18 16:33:45

--
-- PostgreSQL database dump complete
--


