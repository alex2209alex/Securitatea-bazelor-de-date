-- CREATE USER aplicatie_java IDENTIFIED BY "1234";
--
-- GRANT CREATE SESSION TO aplicatie_java;
--
-- GRANT SELECT, INSERT
--     ON utilizatori
--     TO aplicatie_java;
--
-- GRANT SELECT, INSERT
--     ON chei_criptari_cnpuri
--     TO aplicatie_java;
--
-- GRANT SELECT, INSERT
--     ON conturi_utilizatori
--     TO aplicatie_java;
--
-- GRANT SELECT, INSERT
--     ON conturi
--     TO aplicatie_java;
--
-- GRANT SELECT, INSERT, UPDATE
--     ON credite
--     TO aplicatie_java;
--
-- GRANT SELECT ANY SEQUENCE TO aplicatie_java;
--
-- GRANT EXECUTE ANY PROCEDURE TO aplicatie_java;
--
-- GRANT SELECT, INSERT, UPDATE, DELETE
--     ON "flyway_schema_history"
--     TO aplicatie_java;

SELECT * FROM utilizatori;

DELETE FROM conturi WHERE 1=1;
