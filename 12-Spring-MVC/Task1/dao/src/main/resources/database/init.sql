DROP TABLE IF EXISTS persons;

CREATE TABLE IF NOT EXISTS persons
(
    id INT8 PRIMARY KEY auto_increment,
    firstName VARCHAR(30),
    lastName VARCHAR(30),
    age INT,
    passportNumber VARCHAR(20)
);

INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('John', 'Williams', 28, 'CD1231232L');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Elle', 'Green', 17, 'BH73453546');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Liam', 'Cooper', 34, 'QW23212312');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Eleanor', 'Hughes', 23, 'FG43234F23');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Ryan', 'Harrison', 42, 'FF23Q23B43');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Amelia', 'Smith', 56, 'QWSA6734FJ');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Hurry', 'Taylor', 24, 'J5DSLDF562');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Josh', 'Morgan', 37, 'FGFS4246H7');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Luke', 'Clark', 25, '5KDFSEDD43');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Matthew', 'Ward', 36, 'GDSDGDWERH');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('John', 'Ward', 18, 'QW12345678');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Elle', 'Clark', 19, 'WE87654321');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Liam', 'Morgan', 21, 'ER132543689');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Eleanor', 'Taylor', 63, 'JK09876543');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Ryan', 'Smith', 47, 'CX65473829');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Amelia', 'Harrison', 56, 'TY3456GH29');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Hurry', 'Hughes', 48, 'OL35489JN2');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Josh', 'Cooper', 32, '034FG87SVD');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Luke', 'Green', 19, 'FGTSNU83R5');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Matthew', 'Williams', 21, 'HG4894CDSA');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('John', 'Ward', 78, 'CVFE3RSW23');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Elle', 'Williams', 42, '768HYUJ235');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Liam', 'Clark', 39, 'GH7823CVLO');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Eleanor', 'Green', 42, '906HBG4RFD');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Ryan', 'Morgan', 34, 'CVFRTGBSER');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Amelia', 'Cooper', 27, '78IUYTGFBE');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Hurry', 'Smith', 29, '5619MJDPQ4');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Josh', 'Hughes', 30, 'DER456NZZA');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Luke', 'Harrison', 24, 'QW28JGBVF5');
INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES ('Matthew', 'Smith', 25, '04456HBFGR');