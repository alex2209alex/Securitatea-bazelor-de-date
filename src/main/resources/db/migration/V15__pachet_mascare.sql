CREATE OR REPLACE PACKAGE pachet_mascare
IS
    FUNCTION f_mascare(
        sir VARCHAR2
    ) RETURN VARCHAR2;
    FUNCTION f_mascare(
        nr NUMBER
    ) RETURN NUMBER;
END pachet_mascare;
/

CREATE OR REPLACE PACKAGE BODY pachet_mascare
IS
    TYPE tip_tabind IS TABLE OF NUMBER INDEX BY PLS_INTEGER;
    v_tabind tip_tabind;
    FUNCTION f_mascare(sir VARCHAR2)
        RETURN VARCHAR2
        IS
        v_sir  VARCHAR2(100);
        v_lung NUMBER;
    BEGIN
        v_sir := SUBSTR(sir, 1, 1);
        SELECT LENGTH(sir)
        INTO v_lung
        FROM dual;
        v_sir := RPAD(v_sir, v_lung, '*');
        RETURN v_sir;
    END f_mascare;
    FUNCTION f_mascare(nr NUMBER)
        RETURN NUMBER
        IS
        lung    NUMBER;
        minnou  NUMBER;
        maxnou  NUMBER;
        l_seed  VARCHAR2(100);
        v_nrnou NUMBER;
    BEGIN
        IF v_tabind.EXISTS(nr) THEN
            RETURN v_tabind(nr);
        ELSE
            lung := LENGTH(TO_CHAR(nr));
            minnou := TO_NUMBER(
                    RPAD(SUBSTR(TO_CHAR(nr), 1, 1), lung, '0')
                      );
            maxnou := TO_NUMBER(
                    RPAD(SUBSTR(TO_CHAR(nr), 1, 1), lung, '9')
                      );
            l_seed := TO_CHAR(
                            SYSTIMESTAMP,
                            'YYYYDDMMHH24MISSFFFF'
                      );
            DBMS_RANDOM.SEED(val => l_seed);
            v_nrnou := ROUND(
                    DBMS_RANDOM.VALUE(low => minnou, high => maxnou),
                    0
                       );

            v_tabind(nr) := v_nrnou;
            RETURN v_nrnou;
        END IF;
    END f_mascare;
END pachet_mascare;
/

-- expdp sbd/sbd@orclpdb tables=roluri,utilizatori remap_data=utilizatori.nume:pachet_mascare.f_mascare remap_data=utilzatori.prenume:pachet_mascare.f_mascare remap_data=utilizatori.username:pachet_mascare.f_mascare remap_data=utilzatori.id:pachet_mascare.f_mascare directory=DIREXP dumpfile=FISEXPORT.dmp
-- impdp sbd/sbd@orclpdb tables=roluri,utilizatori remap_data=utilizatori.nume:pachet_mascare.f_mascare remap_data=utilzatori.prenume:pachet_mascare.f_mascare remap_data=utilizatori.username:pachet_mascare.f_mascare remap_data=utilzatori.id:pachet_mascare.f_mascare directory=DIREXP dumpfile=FISEXPORT.dmp
