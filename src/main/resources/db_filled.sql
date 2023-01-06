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
    id       INT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(50) NOT NULL,
    capacity INT         NOT NULL,
    number_of_ports INT    NOT NULL
);

CREATE TABLE staff
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    surname    VARCHAR(50) NOT NULL,
    ship_id    INT,
    FOREIGN KEY (ship_id) REFERENCES ship (id)
        on update cascade
        on delete cascade
);

CREATE TABLE city
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(100) NOT NULL,
    country VARCHAR(50)  NOT NULL
);

CREATE TABLE cruise
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    ship_id         INT,
    duration        INT,
    price           DOUBLE NOT NULL,
    start_day       datetime,
    paid            BOOLEAN,
    start_port      INT    NOT NULL,
    end_port        INT    NOT NULL,
    FOREIGN KEY (ship_id) REFERENCES ship (id)
        on update cascade
        on delete cascade,
    FOREIGN KEY (start_port) REFERENCES city (id)
        on update cascade
        on delete cascade,
    FOREIGN KEY (end_port) REFERENCES city (id)
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
        on delete cascade,
    FOREIGN KEY (user_id) REFERENCES user (id)
        on update cascade
        on delete cascade
);

CREATE TABLE ship_has_cities
(
    ship_id INT,
    city_id INT,
    FOREIGN KEY (ship_id) REFERENCES ship (id)
        on update cascade
        on delete cascade,
    FOREIGN KEY (city_id) REFERENCES city (id)
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

INSERT INTO ship(name, capacity, number_of_ports)
VALUES ('MSC Fantasia', 3959, 5),
       ('MSC Magnifica', 3013, 8),
       ('MSC Preziosa', 4363, 12),
       ('MSC Passion', 5, 8),
       ('MSC Sentiaro', 2490, 7);

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

INSERT INTO cruise(ship_id, duration, price, start_day, paid, start_port, end_port)
VALUES (1, 4, 6300, '2023-04-30', true,  7, 8),
       (2, 7, 12000, '2023-06-04', true, 1, 6),
       (3, 11, 22220, '2023-05-28', true, 13, 1),
       (5, 10, 11300, '2023-08-12', true, 4, 5),
       (4, 7, 9000, '2023-07-10', true,  11, 7),
       (3, 9, 19200, '2023-09-1', true,  4, 5),
       (1, 11, 31999, '2023-05-31', true, 6, 2);

INSERT INTO transaction(cruise_id, timestamp, amount, completed, description)
VALUES (1, current_date, 11714, true, 'Succesfully paid'),
       (2, current_date, 18938, true, 'Succesfully paid'),
       (3, current_date, 32000, true, 'Succesfully paid');

INSERT INTO role(name)
VALUES ('CLIENT'),
       ('ADMIN'),
       ('MANAGER');

INSERT INTO user(id, login, password, first_name, surname, role_id, blocked)
VALUES (1, 'innakamar@gmail.com', 'innakamar', 'Inna', 'Kamarenko', 1, false),
       (2, 'lusenko@gmail.com', 'lusenko', 'Tamara', 'Lusenko', 1, false),
       (3, 'gullyle@gmail.com', 'gullyle', 'Halyna', 'Hnatyuk', 1, false),
       (4, 'nazar29meln@gmail.com', 'melnych123', 'Nazar', 'Melnychenko', 1, false),
       (5, 'victoradm1@gmail.com', 'sereda', 'Victor', 'Sereda', 2, false),
       (6, 'dinakram23@gmail.com', 'innakamar', 'Diana', 'Kramarenko', 3, false);
select *
from user;

INSERT INTO users_has_cruises(user_id, cruise_id)
VALUES (1, 2),
       (2, 1),
       (3, 2),
       (4, 2);

# route
INSERT INTO ship_has_cities(ship_id, city_id)
VALUES (1, 7),
       (1, 3),
       (1, 1),
       (1, 5),
       (1, 8),
       (2, 1),
       (2, 5),
       (2, 3),
       (2, 10),
       (2, 9),
       (2, 11),
       (2, 7),
       (2, 6),
       (3, 13),
       (3, 2),
       (3, 4),
       (3, 10),
       (3, 14),
       (3, 2),
       (3, 8),
       (3, 9),
       (3, 12),
       (3, 11),
       (3, 5),
       (3, 1),
       (4, 11),
       (4, 8),
       (4, 12),
       (4, 4),
       (4, 1),
       (4, 9),
       (4, 2),
       (4, 7),
       (5, 4),
       (5, 9),
       (5, 10),
       (5, 2),
       (5, 6),
       (5, 5);








