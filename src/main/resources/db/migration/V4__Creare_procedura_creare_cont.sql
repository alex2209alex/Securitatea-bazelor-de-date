CREATE OR REPLACE PROCEDURE creare_cont(
    nume IN VARCHAR2,
    prenume IN VARCHAR2,
    cnp IN VARCHAR2,
    username IN VARCHAR2,
    parola IN VARCHAR2,
    rol IN VARCHAR2
)
    IS
    id_utilizator                  NUMBER(18);
    cnp_criptat         RAW(16);
    hash_parola         VARCHAR2(64);
    cheie_criptare      RAW(16);
    vector_initializare RAW(16);
BEGIN
    id_utilizator := secventa_utilizatori.NEXTVAL;
    criptare_cnp(cnp, cnp_criptat, cheie_criptare, vector_initializare);
    hashuit(parola, hash_parola);

    INSERT INTO utilizatori
    VALUES (id_utilizator, nume, prenume, cnp_criptat, username, hash_parola, rol);

    INSERT INTO chei_criptari_cnpuri
    VALUES (id_utilizator, cheie_criptare, vector_initializare);
    commit;
END;
