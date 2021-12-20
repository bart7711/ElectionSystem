DELETE FROM candidate where id > 0;
DELETE FROM party where id > 0;

INSERT INTO party (id,ideology,name,symbol) values (1,'Social democracy','Socialdemokratiet','K');
INSERT INTO party (id,ideology,name,symbol) values (2,'Monarchy','Dansk Folkeparti','O');

INSERT INTO candidate (id, full_name,votes,party_id) values (1,'Ted Ask',0,1);
INSERT INTO candidate (id, full_name,votes,party_id) values (2,'Donald Trump',0,1);
INSERT INTO candidate (id, full_name,votes,party_id) values (3,'Adolf Alters',0,2);
