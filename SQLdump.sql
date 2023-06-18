--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.3 (Ubuntu 15.3-1.pgdg22.04+1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: account; Type: TABLE; Schema: public; Owner: lauren.tanudjaya
--

CREATE TABLE public.account (
    account_id integer NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    full_name character varying(255) NOT NULL,
    balance integer
);


ALTER TABLE public.account OWNER TO "lauren.tanudjaya";

--
-- Name: delivery; Type: TABLE; Schema: public; Owner: lauren.tanudjaya
--

CREATE TABLE public.delivery (
    account_id integer,
    deliveryname text,
    deliveryaddress character varying(255) NOT NULL,
    deliveryphonenumber integer NOT NULL
);


ALTER TABLE public.delivery OWNER TO "lauren.tanudjaya";

--
-- Name: food; Type: TABLE; Schema: public; Owner: lauren.tanudjaya
--

CREATE TABLE public.food (
    food_id integer NOT NULL,
    food_name character varying(50),
    food_stock integer
);


ALTER TABLE public.food OWNER TO "lauren.tanudjaya";

--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: lauren.tanudjaya
--

COPY public.account (account_id, username, password, email, full_name, balance) FROM stdin;
1	lauren	$2b$10$hELaqdKht.O6opiWRy1C4uTk2O0AOiFEXoqW.VUJIw3yLbPLNoQIa	lauren@gmail.com	lauren christy	\N
4	tunjung	$2b$10$lw8qG/flxO26Wvgm2QNZhuKaJ9rqx75XarjjLygAcnroNs.uZmCZi	tunjung@gmail.com	Amrita Deviayu	\N
2	laurenz	$2b$10$VamSYXvotEyY56dp9XNOjOHd..Jj2GiPps1eVhpNqsdmc0tHxYuYa	laurenn@gmail.com	lauren christy	200000
3	jeje	$2b$10$SjKW/JAwochgLK0ep1hzsuY8rszcVkFhfOXOgGmvJsUDPoy/gLFwC	juan@gmail.com	Juan Jonathan	10000
\.


--
-- Data for Name: delivery; Type: TABLE DATA; Schema: public; Owner: lauren.tanudjaya
--

COPY public.delivery (account_id, deliveryname, deliveryaddress, deliveryphonenumber) FROM stdin;
4	jeje	alala	132
4	jeje	alala	132
3	a	a	12
3	a	a	12
\N	aaa	lalala	1234
\N	jeje	haduh	8888
\N	2132	wqerw	123
3	aed	ddd	1234
3	aaa	ppp	12345
3	asd	asd	23
\.


--
-- Data for Name: food; Type: TABLE DATA; Schema: public; Owner: lauren.tanudjaya
--

COPY public.food (food_id, food_name, food_stock) FROM stdin;
1	barbecue	3
2	fries	6
3	lamb_soup	4
4	malatang	5
5	noodles	2
6	pho	7
7	pizza	4
8	spicy_fish	5
9	spicy_soup	3
10	taco	2
\.


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: lauren.tanudjaya
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (account_id);


--
-- Name: food food_pkey; Type: CONSTRAINT; Schema: public; Owner: lauren.tanudjaya
--

ALTER TABLE ONLY public.food
    ADD CONSTRAINT food_pkey PRIMARY KEY (food_id);


--
-- PostgreSQL database dump complete
--

