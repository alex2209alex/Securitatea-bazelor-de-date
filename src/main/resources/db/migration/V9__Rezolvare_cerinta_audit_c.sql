CREATE OR REPLACE PROCEDURE procedura_audit_credite(object_schema varchar2, object_name varchar2, policy_name varchar2)
    IS
BEGIN
    DBMS_OUTPUT.put_line('Incercare de modificare a unui credit');
END;
/

BEGIN
    DBMS_FGA.add_policy(
            object_schema=>'sbd',
            object_name=>'credite',
            policy_name=>'POLICY_SCHIMBARE_CREDIT',
            audit_column=>'status',
            enable=> true,
            statement_types=>'UPDATE',
            handler_schema=>'sbd',
            handler_module=>'PROCEDURA_AUDIT_CREDITE'
    );
END;
/
