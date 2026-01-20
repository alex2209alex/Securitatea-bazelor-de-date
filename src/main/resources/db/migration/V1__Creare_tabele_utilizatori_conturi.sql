CREATE TABLE roluri
(
    rol VARCHAR2(10) NOT NULL PRIMARY KEY
);

INSERT INTO roluri
VALUES ('CLIENT');

INSERT INTO roluri
VALUES ('ANGAJAT');

INSERT INTO roluri
VALUES ('ANGAJAT_HR');

INSERT INTO roluri
VALUES ('MANAGER');

CREATE SEQUENCE secventa_utilizatori
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE utilizatori
(
    id          NUMBER(18)          NOT NULL PRIMARY KEY,
    nume        VARCHAR2(50)        NOT NULL,
    prenume     VARCHAR2(50)        NOT NULL,
    cnp         RAW(16),
    username    VARCHAR2(50) UNIQUE NOT NULL,
    hash_parola VARCHAR2(64)        NOT NULL,
    rol         VARCHAR2(10),
    CONSTRAINT fk_rol
        FOREIGN KEY (rol)
            REFERENCES roluri (rol)
);

CREATE TABLE chei_criptari_cnpuri
(
    id_utilizator       NUMBER(18) NOT NULL PRIMARY KEY,
    cheie_criptare      RAW(16)    NOT NULL,
    vector_initializare RAW(16)    NOT NULL,
    CONSTRAINT fk_id_utilizator
        FOREIGN KEY (id_utilizator)
            REFERENCES utilizatori (id)
);

CREATE TABLE tipuri_de_conturi
(
    tip_cont VARCHAR2(10) NOT NULL PRIMARY KEY
);

INSERT INTO tipuri_de_conturi
VALUES ('INTERN');

INSERT INTO tipuri_de_conturi
VALUES ('EXTERN');

CREATE SEQUENCE secventa_conturi
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE conturi
(
    id       NUMBER(18)   NOT NULL PRIMARY KEY,
    suma     DECIMAL      NOT NULL,
    tip_cont VARCHAR2(10) NOT NULL,
    CONSTRAINT fk_tip_cont
        FOREIGN KEY (tip_cont)
            REFERENCES tipuri_de_conturi (tip_cont)
);
