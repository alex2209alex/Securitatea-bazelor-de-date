CALL creare_cont('Angajat', 'Simplu', '2234567890123', 'angajat', 'angajat', 'ANGAJAT');
/

CALL creare_cont('Angajat', 'HR', '3234567890123', 'angajat_hr', 'angajat_hr', 'ANGAJAT_HR');
/

CALL creare_cont('Manager', 'Manager', '4234567890123', 'manager', 'manager', 'MANAGER');
/

INSERT INTO conturi
VALUES (secventa_conturi.nextval, 100, 'INTERN', 'RO111000000000000000000');

INSERT INTO conturi
VALUES (secventa_conturi.nextval, 100, 'INTERN', 'RO111000000000000000001');
