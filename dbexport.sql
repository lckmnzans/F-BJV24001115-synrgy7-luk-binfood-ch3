--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

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
-- Name: binfud; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA binfud;


ALTER SCHEMA binfud OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: merchant; Type: TABLE; Schema: binfud; Owner: postgres
--

CREATE TABLE binfud.merchant (
    id uuid NOT NULL,
    merchant_name varchar NOT NULL,
    merchant_location varchar NOT NULL,
    "open" boolean NOT NULL
);


ALTER TABLE binfud.merchant OWNER TO postgres;

--
-- Name: order; Type: TABLE; Schema: binfud; Owner: postgres
--

CREATE TABLE binfud."order" (
    id uuid NOT NULL,
    order_time timestamp with time zone NOT NULL,
    destination_address varchar NOT NULL,
    user_id uuid NOT NULL,
    completed boolean NOT NULL
);


ALTER TABLE binfud."order" OWNER TO postgres;

--
-- Name: order_detail; Type: TABLE; Schema: binfud; Owner: postgres
--

CREATE TABLE binfud.order_detail (
    id uuid NOT NULL,
    order_id uuid NOT NULL,
    product_id uuid NOT NULL,
    quantity integer,
    total_price decimal
);


ALTER TABLE binfud.order_detail OWNER TO postgres;

--
-- Name: product; Type: TABLE; Schema: binfud; Owner: postgres
--

CREATE TABLE binfud.product (
    id uuid NOT NULL,
    product_name varchar NOT NULL,
    price decimal NOT NULL,
    merchant_id uuid NOT NULL
);


ALTER TABLE binfud.product OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: binfud; Owner: postgres
--

CREATE TABLE binfud.users (
    id uuid NOT NULL,
    username varchar NOT NULL,
    email_address varchar NOT NULL,
    "password" varchar NOT NULL
);


ALTER TABLE binfud.users OWNER TO postgres;

--
-- Data for Name: merchant; Type: TABLE DATA; Schema: binfud; Owner: postgres
--

COPY binfud.merchant (id, merchant_name, merchant_location, open) FROM stdin;
\.


--
-- Data for Name: order; Type: TABLE DATA; Schema: binfud; Owner: postgres
--

COPY binfud."order" (id, order_time, destination_address, user_id, completed) FROM stdin;
\.


--
-- Data for Name: order_detail; Type: TABLE DATA; Schema: binfud; Owner: postgres
--

COPY binfud.order_detail (id, order_id, product_id, quantity, total_price) FROM stdin;
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: binfud; Owner: postgres
--

COPY binfud.product (id, product_name, price, merchant_id) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: binfud; Owner: postgres
--

COPY binfud.users (id, username, email_address, password) FROM stdin;
\.


--
-- Name: users email_address_unique; Type: CONSTRAINT; Schema: binfud; Owner: postgres
--

ALTER TABLE ONLY binfud.users
    ADD CONSTRAINT email_address_unique UNIQUE (email_address);


--
-- Name: merchant merchant_id; Type: CONSTRAINT; Schema: binfud; Owner: postgres
--

ALTER TABLE ONLY binfud.merchant
    ADD CONSTRAINT merchant_id PRIMARY KEY (id);


--
-- Name: order_detail order_detail_pkey; Type: CONSTRAINT; Schema: binfud; Owner: postgres
--

ALTER TABLE ONLY binfud.order_detail
    ADD CONSTRAINT order_detail_pkey PRIMARY KEY (id);


--
-- Name: order order_pkey; Type: CONSTRAINT; Schema: binfud; Owner: postgres
--

ALTER TABLE ONLY binfud."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: binfud; Owner: postgres
--

ALTER TABLE ONLY binfud.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: users user_id; Type: CONSTRAINT; Schema: binfud; Owner: postgres
--

ALTER TABLE ONLY binfud.users
    ADD CONSTRAINT user_id PRIMARY KEY (id);


--
-- Name: users username_unique; Type: CONSTRAINT; Schema: binfud; Owner: postgres
--

ALTER TABLE ONLY binfud.users
    ADD CONSTRAINT username_unique UNIQUE (username);


--
-- Name: fki_order_id; Type: INDEX; Schema: binfud; Owner: postgres
--

CREATE INDEX fki_order_id ON binfud.order_detail USING btree (order_id);


--
-- Name: product merchant_id; Type: FK CONSTRAINT; Schema: binfud; Owner: postgres
--

ALTER TABLE ONLY binfud.product
    ADD CONSTRAINT merchant_id FOREIGN KEY (merchant_id) REFERENCES binfud.merchant(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: order_detail order_id; Type: FK CONSTRAINT; Schema: binfud; Owner: postgres
--

ALTER TABLE ONLY binfud.order_detail
    ADD CONSTRAINT order_id FOREIGN KEY (order_id) REFERENCES binfud."order"(id);


--
-- Name: order_detail product_id; Type: FK CONSTRAINT; Schema: binfud; Owner: postgres
--

ALTER TABLE ONLY binfud.order_detail
    ADD CONSTRAINT product_id FOREIGN KEY (product_id) REFERENCES binfud.product(id);


--
-- Name: order user_id; Type: FK CONSTRAINT; Schema: binfud; Owner: postgres
--

ALTER TABLE ONLY binfud."order"
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES binfud.users(id);


--
-- PostgreSQL database dump complete
--

