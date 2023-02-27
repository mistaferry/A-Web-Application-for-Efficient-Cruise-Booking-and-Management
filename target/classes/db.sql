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