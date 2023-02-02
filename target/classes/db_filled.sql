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
    login      VARCHAR(50) NOT NULL UNIQUE ,
    password   VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    surname    VARCHAR(50) NOT NULL,
    role_id    INT DEFAULT 1,
    blocked    BOOLEAN DEFAULT FALSE,
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
    surname    VARCHAR(50) NOT NULL
);

CREATE TABLE ship_has_staff(
                               staff_id INT NOT NULL,
                               ship_id INT NOT NULL,
                               FOREIGN KEY (staff_id) REFERENCES staff (id)
                                   on update cascade
                                   on delete cascade,
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
    number_of_register_people INT NOT NULL,
    FOREIGN KEY (ship_id) REFERENCES ship (id)
        on update cascade
        on delete cascade
);

CREATE TABLE users_has_cruises
(
    user_id   INT,
    cruise_id INT,
    date_of_registration DATETIME DEFAULT (current_date),
    paid boolean DEFAULT FALSE,
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
VALUES ('MSC Fantasia', 5, 5),
       ('MSC Magnifica', 3013, 8),
       ('MSC Preziosa', 4363, 12),
       ('MSC Passion', 3959, 8),
       ('MSC Sentiaro', 2490, 7);

INSERT INTO staff(first_name, surname)
VALUES ('Adam', 'Romanchenko'),
       ('Larisa', 'Vasiliev'),
       ('Sofia', 'Panasyuk'),
       ('Ruslan', 'Shevchuk'),
       ('Viktor', 'Antonenko'),
       ('Margarita', 'Kravchuk'),
       ('Mykhailo', 'Mykytyuk'),
       ('Ruslan', 'Shevchenko'),
       ('Josyp', 'Brovarchuk'),
       ('Olga', 'Miroshnychenko');

INSERT INTO cruise(ship_id, duration, price, start_day, number_of_register_people)
VALUES (1, 4, 6300, '2023-04-30', 4),
       (2, 8, 12000, '2023-04-30', 4),
       (3, 12, 22220, '2023-04-30', 2),
       (5, 8, 11300, '2023-04-30', 2),
       (4, 8, 9000, '2023-04-30', 4),
       (3, 12, 19200, '2023-09-01', 1),
       (1, 12, 31999, '2023-05-31', 1);

INSERT INTO role(name)
VALUES ('CLIENT'),
       ('ADMIN'),
       ('MANAGER');

INSERT INTO user(login, password, first_name, surname, role_id, blocked)
VALUES ('huryn@gmail.com', 'huryn', 'Inna', 'Kamarenko', 1, false),
       ('lusenko@gmail.com', 'lusenko', 'Tamara', 'Lusenko', 1, false),
       ('gullyle@gmail.com', 'gullyle', 'Halyna', 'Hnatyuk', 1, false),
       ('nazar29meln@gmail.com', 'melnych123', 'Nazar', 'Melnychenko', 1, false),
       ('victoradm1@gmail.com', 'sereda', 'Victor', 'Sereda', 2, false),
       ('dinakram23@gmail.com', 'innakamar', 'Diana', 'Kramarenko', 2, false),
       ('user@gmail.com', 'user', 'User', 'User', 1, false),
       ('admin@gmail.com', 'admin', 'Admin', 'Admin', 2, false);
select *
from user;

INSERT INTO users_has_cruises(user_id, cruise_id, date_of_registration, paid)
VALUES (1, 2, '2023-01-12', true),
       (2, 1, '2023-01-12', true),
       (3, 5, '2023-01-03', true),
       (4, 5, '2022-12-10', true),
       (5, 5, '2023-01-11', true),
       (6, 1, '2022-11-01', true),
       (3, 4, '2023-01-23', true),
       (2, 3, '2023-01-04', true),
       (1, 5, '2023-01-19', true),
       (1, 7, '2022-10-29', true),
       (1, 5, '2023-01-25', true),
       (1, 2, '2023-01-05', true),
       (1, 3, '2022-12-30', true),
       (1, 6, '2022-08-05', true);

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

INSERT INTO ship_has_staff (ship_id, staff_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (2, 10),
       (2, 9),
       (2, 8),
       (2, 7),
       (2, 5),
       (2, 1),
       (2, 4),
       (2, 3);

select * from users_has_cruises






