INSERT INTO USER (ACTIVE, EMAIL, FIRSTNAME, LASTNAME, LOCKVERSION, PASSWORD, PHONENUMBER, USERNAME) VALUES (1, 'admin@msggroup.com', 'admin', 'csaba', 1, 'e10adc3949ba59abbe56e057f20f883e', '37476573', 'admin');

INSERT INTO USER (ACTIVE, EMAIL, FIRSTNAME, LASTNAME, LOCKVERSION, PASSWORD, PHONENUMBER, USERNAME) VALUES (1, 'abdmin@msggroup.com', 'szabi', 'ashd', 1, 'e10adc3949ba59abbe56e057f20f883e', '37476573', 'balazs');
INSERT INTO USER (ACTIVE, EMAIL, FIRSTNAME, LASTNAME, LOCKVERSION, PASSWORD, PHONENUMBER, USERNAME) VALUES (1, 'adcmin@msggroup.com', 'gyuri', 'csfdgsaba', 1, 'e10adc3949ba59abbe56e057f20f883e', '37476573', 'attila');
INSERT INTO USER (ACTIVE, EMAIL, FIRSTNAME, LASTNAME, LOCKVERSION, PASSWORD, PHONENUMBER, USERNAME) VALUES (1, 'admdin@msggroup.com', 'tomszelek', 'csdfgaba', 1, 'e10adc3949ba59abbe56e057f20f883e', '37476573', 'attila2');
INSERT INTO USER (ACTIVE, EMAIL, FIRSTNAME, LASTNAME, LOCKVERSION, PASSWORD, PHONENUMBER, USERNAME) VALUES (1, 'admcin@msggroup.com', 'ggggg', 'csdfggaba', 1, 'e10adc3949ba59abbe56e057f20f883e', '37476573', 'szabi');
INSERT INTO USER (ACTIVE, EMAIL, FIRSTNAME, LASTNAME, LOCKVERSION, PASSWORD, PHONENUMBER, USERNAME) VALUES (0, 'peter@msggroup.com', 'admin2', 'peter', 1, MD5('admin'), '37476573', 'admin2');

INSERT INTO PERMISSION (NAME,LOCKVERSION,DETAIL) VALUES ('permission.management',1,'add permissions...');
INSERT INTO PERMISSION (NAME,LOCKVERSION,DETAIL) VALUES ('user.management',1,'detail');
INSERT INTO PERMISSION (NAME,LOCKVERSION,DETAIL) VALUES ('bug.management',1,'detail');
INSERT INTO PERMISSION (NAME,LOCKVERSION,DETAIL) VALUES ('bug.close',1,'detail');
INSERT INTO PERMISSION (NAME,LOCKVERSION,DETAIL) VALUES ('bug.export',1,'detail');

INSERT INTO ROLE(LOCKVERSION,NAME) VALUES (1,'role.administrator');
INSERT INTO ROLE(LOCKVERSION,NAME) VALUES (1,'role.projectmanager');
INSERT INTO ROLE(LOCKVERSION,NAME)VALUES (1,'role.testmanager');
INSERT INTO ROLE(LOCKVERSION,NAME) VALUES (1,'role.developer');
INSERT INTO ROLE(LOCKVERSION,NAME) VALUES (1,'role.tester');

INSERT INTO USER_ROLE VALUES (1, 1);
INSERT INTO USER_ROLE VALUES (1, 5);

INSERT INTO ROLE_PERMISSION VALUES (1, 1);
INSERT INTO ROLE_PERMISSION VALUES (1, 2);
INSERT INTO ROLE_PERMISSION VALUES (1, 3);
INSERT INTO ROLE_PERMISSION VALUES (1, 4);
INSERT INTO ROLE_PERMISSION VALUES (1, 5);
INSERT INTO ROLE_PERMISSION VALUES (2, 3);
INSERT INTO ROLE_PERMISSION VALUES (2, 4);
INSERT INTO ROLE_PERMISSION VALUES (3, 3);
INSERT INTO ROLE_PERMISSION VALUES (3, 4);
INSERT INTO ROLE_PERMISSION VALUES (4, 3);
INSERT INTO ROLE_PERMISSION VALUES (5, 3);

INSERT INTO bug (ATTACHMENT, ATTACHMENTNAME, DESCRIPTION, FIXEDIN, LOCKVERSION, SEVERITY, STATUS, TARGETDATE, TITLE, VERSION, ASSIGNED_ID, AUTHOR_ID) VALUES (NULL, NULL, 'Desc', 'v1.1', 1, 1, 0, '2017-09-03', 'New Bug', 'v1', 1, 1), (NULL, NULL, 'asdasd', 'v1.1', 1, 3, 0, '2017-09-18', 'Other Bug', 'v1', 3, 3), (NULL, NULL, 'Hopa', 'v1.2', 1, 2, 0, '2017-09-20', 'Test Bug', 'v1.1', 6, 6);
