CREATE OR REPLACE PROCEDURE criptare_cnp(
    cnp_necriptat IN VARCHAR2,
    cnp_criptat OUT RAW,
    cheie_criptare OUT RAW,
    vector_initializare OUT RAW
)
    IS
    mod_operare PLS_INTEGER;
BEGIN
    cheie_criptare := DBMS_CRYPTO.RANDOMBYTES(16);
    vector_initializare := DBMS_CRYPTO.RANDOMBYTES(16);

    mod_operare := DBMS_CRYPTO.ENCRYPT_AES128 +
                   DBMS_CRYPTO.PAD_PKCS5 +
                   DBMS_CRYPTO.CHAIN_CBC;

    cnp_criptat := DBMS_CRYPTO.ENCRYPT(
            utl_i18n.string_to_raw(cnp_necriptat, 'AL32UTF8'),
            mod_operare,
            cheie_criptare,
            vector_initializare);
    commit;
END;
