ALTER TABLE conturi
    ADD iban VARCHAR2(24) NOT NULL;

CREATE TABLE conturi_utilizatori
(
    id_cont       NUMBER(18) NOT NULL,
    id_utilizator NUMBER(18) NOT NULL,
    PRIMARY KEY (id_cont, id_utilizator),
    CONSTRAINT fk_cont FOREIGN KEY (id_cont)
        REFERENCES conturi (id),
    CONSTRAINT fk_utilizator FOREIGN KEY (id_utilizator)
        REFERENCES utilizatori (id)
);

CREATE TABLE statusuri_credite
(
    status VARCHAR2(20) NOT NULL PRIMARY KEY
);

INSERT INTO statusuri_credite
VALUES ('ASTEAPTA_APROBARE');

INSERT INTO statusuri_credite
VALUES ('RESPINS');

INSERT INTO statusuri_credite
VALUES ('APROBAT');

CREATE SEQUENCE seq_credite
    START WITH 1 INCREMENT BY 1;

CREATE TABLE credite
(
    id                 NUMBER(18)   NOT NULL PRIMARY KEY,
    suma               NUMBER(7)    NOT NULL,
    id_cont_utilizator NUMBER(18)   NOT NULL,
    id_cont_restituire NUMBER(18),
    status             VARCHAR2(20) NOT NULL,
    CONSTRAINT fk_cont_livrare FOREIGN KEY (id_cont_utilizator)
        REFERENCES conturi (id),
    CONSTRAINT fk_cont_restituire FOREIGN KEY (id_cont_restituire)
        REFERENCES conturi (id),
    CONSTRAINT fk_status FOREIGN KEY (status)
        REFERENCES statusuri_credite (status)
);
