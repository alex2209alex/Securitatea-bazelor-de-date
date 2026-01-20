CREATE OR REPLACE FUNCTION decriptare_cnp
(
    cnp_criptat RAW,
    cheie_criptare RAW,
    vector_initializare RAW
)
    RETURN VARCHAR2
    IS
    mod_operare PLS_INTEGER;
    cnp_raw     RAW(16);
BEGIN
    mod_operare := DBMS_CRYPTO.ENCRYPT_AES128 +
                   DBMS_CRYPTO.PAD_PKCS5 +
                   DBMS_CRYPTO.CHAIN_CBC;

    cnp_raw := DBMS_CRYPTO.DECRYPT(
            cnp_criptat,
            mod_operare,
            cheie_criptare,
            vector_initializare);

    RETURN utl_i18n.raw_to_char(cnp_raw, 'AL32UTF8');
END;
