------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------- Create schema --------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS employeeProjectCorrelation;
DROP TABLE IF EXISTS employeePersonalInfo;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS unit;
DROP TABLE IF EXISTS project;

CREATE TABLE IF NOT EXISTS project
(
    id INT8 PRIMARY KEY auto_increment,
    name VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS unit
(
    id INT8 PRIMARY KEY auto_increment,
    name VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS employee
(
    id INT8 PRIMARY KEY auto_increment,
    firstName VARCHAR(30),
    lastName VARCHAR(30),
    status varchar(20),
    street varchar(50),
    flatNumber INT,
    houseNumber INT,
    unit INT8,
    foreign key (unit) references unit(id)
);

CREATE TABLE IF NOT EXISTS employeePersonalInfo
(
    id INT8 PRIMARY KEY auto_increment,
    age INT
);

CREATE TABLE IF NOT EXISTS employeeProjectCorrelation
(
    id INT8 PRIMARY KEY auto_increment,
    employeeID INT8,
    projectID INT8,
    foreign key (employeeID) references employee(id),
    foreign key (projectID) references project(id),
);

------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------- DB Population --------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO project(name) VALUES('Green Project');
INSERT INTO project(name) VALUES('Red Project');
INSERT INTO project(name) VALUES('Blue Project');

INSERT INTO unit(name) VALUES('Unit 8');
INSERT INTO unit(name) VALUES('Unit 547');
INSERT INTO unit(name) VALUES('Unit 23');

INSERT INTO employee(firstName, lastName, status, street, flatNumber, houseNumber, unit) VALUES('John', 'Williams', 'ACTIVE', 'Great St.', 12, 87, 1);
INSERT INTO employee(firstName, lastName, status, street, flatNumber, houseNumber, unit) VALUES('Elle', 'Green', 'ACTIVE', 'Johns St.', 98, 1, 1);
INSERT INTO employee(firstName, lastName, status, street, flatNumber, houseNumber, unit) VALUES('Liam', 'Cooper', 'ACTIVE', 'Bourbon St.', 1, 2, 2);
INSERT INTO employee(firstName, lastName, status, street, flatNumber, houseNumber, unit) VALUES('Eleanor', 'Hughes', 'ACTIVE', 'Lombard St.', 15, 36, 2);
INSERT INTO employee(firstName, lastName, status, street, flatNumber, houseNumber, unit) VALUES('Ryan', 'Harrison', 'ACTIVE', 'Vermont St.', 96, 45, 3);
INSERT INTO employee(firstName, lastName, status, street, flatNumber, houseNumber, unit) VALUES('Amelia', 'Smith', 'ACTIVE', 'Mulholland Drive St.', 14, 65, 3);

INSERT INTO employeePersonalInfo(age) VALUES(34);
INSERT INTO employeePersonalInfo(age) VALUES(23);
INSERT INTO employeePersonalInfo(age) VALUES(48);
INSERT INTO employeePersonalInfo(age) VALUES(23);
INSERT INTO employeePersonalInfo(age) VALUES(27);
INSERT INTO employeePersonalInfo(age) VALUES(32);

INSERT INTO employeeProjectCorrelation(employeeID, projectID) VALUES(1, 3);
INSERT INTO employeeProjectCorrelation(employeeID, projectID) VALUES(2, 3);
INSERT INTO employeeProjectCorrelation(employeeID, projectID) VALUES(3, 2);
INSERT INTO employeeProjectCorrelation(employeeID, projectID) VALUES(4, 2);
INSERT INTO employeeProjectCorrelation(employeeID, projectID) VALUES(5, 1);
INSERT INTO employeeProjectCorrelation(employeeID, projectID) VALUES(6, 1);