INSERT INTO "USER" (ID, USERNAME, PASSWORD, BLOCKED)
VALUES (1, 'Stuart', 'Password', 0 );


INSERT INTO "AUTHORITY" (ID, AUTHORITY)
VALUES (100, 'ROLE_ADMIN');

INSERT INTO "AUTHORITY" (ID, AUTHORITY)
VALUES (101, 'ROLE_USER');


INSERT INTO "USER_AUTHORITIES" (USER_ID, AUTHORITIES_ID)
VALUES(1,100);

INSERT INTO "USER_AUTHORITIES" (USER_ID, AUTHORITIES_ID)
VALUES(1,101);