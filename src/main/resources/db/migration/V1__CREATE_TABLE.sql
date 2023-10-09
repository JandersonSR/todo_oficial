
--CREATE TABLE todos (
--    id integer primary key AUTO_INCREMENT,
--    peso INTEGER,
--    description varchar(25),
--    createdAt TIMESTAMP,
--    userId varchar(25) REFERENCES users (userId)
--);
--
--CREATE TABLE users (
--    id integer primary key AUTO_INCREMENT,
--    userId varchar(25),
--    nome varchar(100),
--    systemId varchar(25),
--    createdAt TIMESTAMP
--);

CREATE TABLE cars (
    id INT NOT NULL PRIMARY KEY,
    license_plate VARCHAR NOT NULL,
    color VARCHAR NOT NULL
);
