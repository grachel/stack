--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.5
-- Dumped by pg_dump version 9.6.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: badges; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE badges (
    id integer NOT NULL,
    userid integer,
    title text,
    creationdate timestamp without time zone DEFAULT now()
);


ALTER TABLE badges OWNER TO postgres;

--
-- Name: commentaries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE commentaries (
    id integer NOT NULL,
    postid integer,
    score integer,
    body text,
    creationdate timestamp without time zone DEFAULT now(),
    userid integer
);


ALTER TABLE commentaries OWNER TO postgres;

--
-- Name: postlinks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE postlinks (
    id integer NOT NULL,
    postid integer,
    relatedpostid integer,
    creationdate timestamp without time zone DEFAULT now()
);


ALTER TABLE postlinks OWNER TO postgres;

--
-- Name: posts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE posts (
    id integer NOT NULL,
    posttype text NOT NULL,
    acceptedanswerid integer,
    parentid integer,
    creationdate timestamp without time zone DEFAULT now(),
    score integer,
    body text NOT NULL,
    owneruserid integer,
    lasteditoruserid integer,
    lasteditdate timestamp without time zone,
    title text,
    tags text,
    closeddate timestamp without time zone,
    closedreason text
);


ALTER TABLE posts OWNER TO postgres;

--
-- Name: posttags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE posttags (
    postid integer,
    tagid integer
);


ALTER TABLE posttags OWNER TO postgres;

--
-- Name: tags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tags (
    id integer NOT NULL,
    tag text
);


ALTER TABLE tags OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id integer NOT NULL,
    reputation integer,
    creationdate timestamp without time zone DEFAULT now(),
    displayname text NOT NULL,
    lastaccessdate timestamp without time zone,
    websiteurl text,
    aboutme text,
    upvotes integer,
    downvotes integer,
    email text NOT NULL
);


ALTER TABLE users OWNER TO postgres;

--
-- Name: votes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE votes (
    id integer NOT NULL,
    postid integer,
    userid integer,
    creationdate timestamp without time zone DEFAULT now()
);


ALTER TABLE votes OWNER TO postgres;

--
-- Data for Name: badges; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY badges (id, userid, title, creationdate) FROM stdin;
\.


--
-- Data for Name: commentaries; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY commentaries (id, postid, score, body, creationdate, userid) FROM stdin;
\.


--
-- Data for Name: postlinks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY postlinks (id, postid, relatedpostid, creationdate) FROM stdin;
\.


--
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY posts (id, posttype, acceptedanswerid, parentid, creationdate, score, body, owneruserid, lasteditoruserid, lasteditdate, title, tags, closeddate, closedreason) FROM stdin;
\.


--
-- Data for Name: posttags; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY posttags (postid, tagid) FROM stdin;
\.


--
-- Data for Name: tags; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tags (id, tag) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, reputation, creationdate, displayname, lastaccessdate, websiteurl, aboutme, upvotes, downvotes, email) FROM stdin;
\.


--
-- Data for Name: votes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY votes (id, postid, userid, creationdate) FROM stdin;
\.


--
-- Name: badges badges_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY badges
    ADD CONSTRAINT badges_pkey PRIMARY KEY (id);


--
-- Name: commentaries commentaries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY commentaries
    ADD CONSTRAINT commentaries_pkey PRIMARY KEY (id);


--
-- Name: postlinks postlinks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY postlinks
    ADD CONSTRAINT postlinks_pkey PRIMARY KEY (id);


--
-- Name: posts posts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (id);


--
-- Name: tags tags_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: votes votes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY votes
    ADD CONSTRAINT votes_pkey PRIMARY KEY (id);


--
-- Name: badges badges_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY badges
    ADD CONSTRAINT badges_userid_fkey FOREIGN KEY (userid) REFERENCES users(id);


--
-- Name: commentaries commentaries_postid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY commentaries
    ADD CONSTRAINT commentaries_postid_fkey FOREIGN KEY (postid) REFERENCES posts(id);


--
-- Name: commentaries commentaries_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY commentaries
    ADD CONSTRAINT commentaries_userid_fkey FOREIGN KEY (userid) REFERENCES users(id);


--
-- Name: postlinks postlinks_postid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY postlinks
    ADD CONSTRAINT postlinks_postid_fkey FOREIGN KEY (postid) REFERENCES posts(id);


--
-- Name: postlinks postlinks_relatedpostid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY postlinks
    ADD CONSTRAINT postlinks_relatedpostid_fkey FOREIGN KEY (relatedpostid) REFERENCES posts(id);


--
-- Name: posts posts_acceptedanswerid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY posts
    ADD CONSTRAINT posts_acceptedanswerid_fkey FOREIGN KEY (acceptedanswerid) REFERENCES posts(id);


--
-- Name: posts posts_lasteditoruserid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY posts
    ADD CONSTRAINT posts_lasteditoruserid_fkey FOREIGN KEY (lasteditoruserid) REFERENCES users(id);


--
-- Name: posts posts_owneruserid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY posts
    ADD CONSTRAINT posts_owneruserid_fkey FOREIGN KEY (owneruserid) REFERENCES users(id);


--
-- Name: posts posts_parentid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY posts
    ADD CONSTRAINT posts_parentid_fkey FOREIGN KEY (parentid) REFERENCES posts(id);


--
-- Name: posttags posttags_postid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY posttags
    ADD CONSTRAINT posttags_postid_fkey FOREIGN KEY (postid) REFERENCES posts(id);


--
-- Name: posttags posttags_tagid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY posttags
    ADD CONSTRAINT posttags_tagid_fkey FOREIGN KEY (tagid) REFERENCES tags(id);


--
-- Name: votes votes_postid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY votes
    ADD CONSTRAINT votes_postid_fkey FOREIGN KEY (postid) REFERENCES posts(id);


--
-- Name: votes votes_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY votes
    ADD CONSTRAINT votes_userid_fkey FOREIGN KEY (userid) REFERENCES users(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

