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

INSERT INTO city(name, country)
VALUES ('Kiel', 'Germany'),
       ('Mons', 'Belgium'),
       ('Honninsvåg', 'Norway'),
       ('Tromse', 'Norway'),
       ('Trondheim', 'Norway'),
       ('Nordfjorden', 'Germany'),
       ('Renny', 'Denmark'),
       ('Gdynia', 'Poland'),
       ('Copenhagen', 'Denmark'),
       ('Tallinn', 'Estonia'),
       ('Helsinki', 'Finland'),
       ('Stockholm', 'Sweden'),
       ('Genoa', 'Italy'),
       ('Marseille', 'France');

INSERT INTO ship(name, capacity)
VALUES ('MSC Fantasia', 3959),
       ('MSC Magnifica', 3013),
       ('MSC Preziosa', 4363);

INSERT INTO staff(first_name, surname, ship_id)
VALUES ('Adam', 'Romanchenko', 1),
       ('Larisa', 'Vasiliev', 1),
       ('Sofia', 'Panasyuk', 2),
       ('Ruslan', 'Shevchuk', 2),
       ('Viktor', 'Antonenko', 1),
       ('Margarita', 'Kravchuk', 1),
       ('Mykhailo', 'Mykytyuk', NULL),
       ('Ruslan', 'Shevchenko', NULL),
       ('Josyp', 'Brovarchuk', 1),
       ('Olga', 'Miroshnychenko', 2);

INSERT INTO cruise(ship_id, duration, start_day, paid, number_of_ports, start_port, end_port)
VALUES (1, 4, '2023-04-30', true, 5, 1, 1),
       (2, 7, '2023-06-04', true, 8, 2, 6),
       (3, 11, '2023-05-28', true, 12, 4, 11);

INSERT INTO transaction(cruise_id, timestamp, amount, completed, description)
VALUES (1, current_date, 11714, true, 'Succesfully paid'),
       (2, current_date, 18938, true, 'Succesfully paid'),
       (3, current_date, 32000, true, 'Succesfully paid');

INSERT INTO role(name)
VALUES ('CLIENT'),
       ('ADMIN'),
       ('MANAGER');

INSERT INTO user(login, password, first_name, surname, role_id, blocked)
VALUES ('innakamar@gmail.com', 'innakamar', 'Inna', 'Kamarenko', 1, false),
       ('lusenko@gmail.com', 'lusenko', 'Tamara', 'Lusenko', 1, false),
       ('gullyle@gmail.com', 'gullyle', 'Halyna', 'Hnatyuk', 1, false),
       ('nazar29meln@gmail.com', 'melnych123', 'Nazar', 'Melnychenko', 1, false),
       ('victoradm1@gmail.com', 'sereda', 'Victor', 'Sereda', 2, false),
       ('dinakram23@gmail.com', 'innakamar', 'Diana', 'Kramarenko', 3, false);

INSERT INTO users_has_cruises(user_id, cruise_id)
VALUES (1, 2),
       (2, 1),
       (3, 2),
       (4, 2);
