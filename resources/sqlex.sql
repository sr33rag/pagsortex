DROP TABLE tbl_students;
CREATE TABLE tbl_students (
  roll INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY,
  firstName VARCHAR(25),
  lastName VARCHAR(25),
  dob DATE NOT NULL,
  marks1 DECIMAL(4,2) DEFAULT 00.00,
  marks2 DECIMAL(4,2) DEFAULT 00.00,
  marks3 DECIMAL(4,2) DEFAULT 00.00,
  percent DECIMAL(4,2) DEFAULT 00.00
);
DROP ROLE web;
CREATE ROLE web;
GRANT SELECT ON tbl_students TO web;
--CREATE ROLE console;
--GRANT web TO console;
--GRANT INSERT,DELETE,UPDATE ON tbl_students TO console;
GRANT web TO user13;

INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Apple','ABC','1989-03-01',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Ball','ABC','1989-03-02',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Cat','ABC','1989-03-03',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Dog','ABC','1989-03-04',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Elephant','ABC','1989-03-05',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Frog','ABC','1989-03-06',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Giraffe','ABC','1989-03-07',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Horse','ABC','1989-03-08',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Icecream','ABC','1989-03-09',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Joker','ABC','1989-03-10',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Kite','ABC','1989-03-11',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Lion','ABC','1989-03-12',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Monkey','ABC','1989-03-13',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Nightingale','ABC','1989-03-14',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Owl','ABC','1989-03-15',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Peacock','ABC','1989-03-16',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Quilt','ABC','1989-03-17',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('RoadRunner','ABC','1989-03-18',60.00,60.00,60.00,60.00);
INSERT INTO tbl_students(firstName,lastName,dob,marks1,marks2,marks3,percent) VALUES ('Snail','ABC','198-03-19',60.00,60.00,60.00,60.00);


CREATE PROCEDURE pageStudents(IN pageNum INTEGER,IN maxRows INTEGER) PARAMETER STYLE JAVA LANGUAGE JAVA DYNAMIC RESULT SETS 1 READS SQL DATA EXTERNAL NAME 'derby.pagesortex.StudentStoredProcs.pageStudents';