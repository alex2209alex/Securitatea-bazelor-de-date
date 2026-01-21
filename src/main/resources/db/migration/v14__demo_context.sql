CREATE CONTEXT aplicatie_ctx USING proced_aplicatie_ctx;

CREATE OR REPLACE PROCEDURE proced_aplicatie_ctx
    IS
BEGIN
    DBMS_SESSION.set_context(
            'APLICATIE_CTX',
            'LAST_LOG_IN',
            TO_CHAR(CURRENT_TIMESTAMP, 'YYYY-MM-DD HH24:MI:SS.FF')
    );
END;
/

CREATE OR REPLACE TRIGGER TR_AFTER_LOGON
    AFTER LOGON
    ON DATABASE
BEGIN
    proced_aplicatie_ctx();
END;
/

SELECT SYS_CONTEXT('APLICATIE_CTX', 'LAST_LOG_IN') AS last_log_in
FROM dual;