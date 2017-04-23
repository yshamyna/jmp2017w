DROP TABLE IF EXISTS persons;

CREATE TABLE IF NOT EXISTS persons
(
    id INT8 PRIMARY KEY auto_increment,
    firstName VARCHAR(30),
    lastName VARCHAR(30),
    birthDate TIMESTAMP,
    hobbies VARCHAR(255)
);

INSERT INTO persons(firstName, lastName, birthDate, hobbies) VALUES ('John', 'Williams', '1976-03-13', NULL);
INSERT INTO persons(firstName, lastName, birthDate, hobbies) VALUES ('Elle', 'Green', '1956-04-18', 'PC games');
INSERT INTO persons(firstName, lastName, birthDate, hobbies) VALUES ('Liam', 'Cooper', '1999-06-21', 'Book reading');
INSERT INTO persons(firstName, lastName, birthDate, hobbies) VALUES ('Eleanor', 'Hughes', '1963-09-17', 'Skiing');
INSERT INTO persons(firstName, lastName, birthDate, hobbies) VALUES ('Ryan', 'Harrison', '1985-01-28', 'Football');
INSERT INTO persons(firstName, lastName, birthDate, hobbies) VALUES ('Amelia', 'Smith', '1978-09-01', 'Be a ghostbuster');
INSERT INTO persons(firstName, lastName, birthDate, hobbies) VALUES ('Hurry', 'Taylor', '1993-05-07', NULL);
INSERT INTO persons(firstName, lastName, birthDate, hobbies) VALUES ('Josh', 'Morgan', '1982-11-15', 'Street racing');
INSERT INTO persons(firstName, lastName, birthDate, hobbies) VALUES ('Luke', 'Clark', '1973-02-21', 'Cooking');
INSERT INTO persons(firstName, lastName, birthDate, hobbies) VALUES ('Matthew', 'Ward', '1984-12-23', 'Programming');