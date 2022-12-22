DROP DATABASE IF EXISTS cruise_company;
CREATE DATABASE IF NOT EXISTS cruise_company;

USE cruise_company;

CREATE TABLE role
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE user
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    login      VARCHAR(50) NOT NULL,
    password   VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    surname    VARCHAR(50) NOT NULL,
    role_id    INT,
    blocked    BOOLEAN,
    FOREIGN KEY (role_id) REFERENCES role (id)
        on update cascade
        on delete cascade
);

CREATE TABLE ship
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL ,
    capacity        INT          NOT NULL
);

CREATE TABLE staff
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    surname    VARCHAR(50) NOT NULL,
    ship_id INT,
    FOREIGN KEY (ship_id) REFERENCES ship (id)
        on update cascade
        on delete cascade
);

CREATE TABLE city
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(100) NOT NULL,
    country VARCHAR(50)  NOT NULL,
    ship_id INT,
    FOREIGN KEY (ship_id) REFERENCES ship (id)
        on update cascade
        on delete cascade
);

CREATE TABLE cruise
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    ship_id   INT,
    duration  INT,
    start_day datetime,
    paid      BOOLEAN,
    number_of_ports INT          NOT NULL,
    start_port      VARCHAR(100) NOT NULL,
    end_port        VARCHAR(100) NOT NULL,
    FOREIGN KEY (ship_id) REFERENCES ship (id)
        on update cascade
        on delete cascade
);

CREATE TABLE transaction
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    cruise_id   INT,
    timestamp   TIMESTAMP,
    amount      double,
    completed   BOOLEAN,
    description VARCHAR(255),
    FOREIGN KEY (cruise_id) REFERENCES cruise (id)
        on update cascade
        on delete cascade
);

CREATE TABLE users_has_cruises
(
    user_id   INT,
    cruise_id INT,
    FOREIGN KEY (cruise_id) REFERENCES cruise (id)
        on update cascade
        on delete cascade
);
