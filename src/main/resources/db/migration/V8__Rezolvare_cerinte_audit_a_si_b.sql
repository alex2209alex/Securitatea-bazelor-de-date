AUDIT TABLE;
/

AUDIT INSERT TABLE;
/

AUDIT DELETE TABLE;
/

AUDIT UPDATE TABLE;
/

AUDIT SELECT TABLE;
/

AUDIT SELECT, INSERT, UPDATE, DELETE ON SBD.utilizatori BY ACCESS WHENEVER NOT SUCCESSFUL;
/

AUDIT ALTER, GRANT, INSERT, UPDATE, DELETE ON DEFAULT;
/

AUDIT CREATE ANY VIEW;
/

AUDIT SESSION;
/

CREATE SEQUENCE seq_audit_credite
    START WITH 1 INCREMENT BY 1;

CREATE TABLE audit_credite
(
    id           NUMBER(10) PRIMARY KEY,
    user_actiune VARCHAR2(30),
    sesiune      NUMBER(10),
    host         VARCHAR2(100),
    timp         DATE,
    status       VARCHAR2(20),
    CONSTRAINT fk_status_audit FOREIGN KEY (status)
        REFERENCES statusuri_credite (status)
);

CREATE OR REPLACE TRIGGER trgigger_audit_update_status_credite
    BEFORE UPDATE OF status
    ON credite
    FOR EACH ROW
BEGIN
    INSERT INTO audit_credite
    VALUES (seq_audit_credite.nextval,
            SYS_CONTEXT('userenv', 'session_user'),
            SYS_CONTEXT('userenv', 'sessionid'),
            SYS_CONTEXT('userenv', 'host'),
            CURRENT_TIMESTAMP,
            :NEW.status);
END;
/

INSERT INTO conturi
VALUES (secventa_conturi.nextval, 100, 'EXTERN', 'RO999000000000000000000');

INSERT INTO conturi_utilizatori
VALUES (1, 1);

INSERT INTO conturi
VALUES (secventa_conturi.nextval, 10000, 'INTERN', 'RO1110000000000000000000');

INSERT INTO credite
VALUES (seq_credite.nextval, 1000, 1, null, 'ASTEAPTA_APROBARE');

UPDATE credite
SET status = 'RESPINS'
WHERE id = 1;
