SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS user CASCADE;

CREATE TABLE user
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    username   VARCHAR(50)  DEFAULT NULL,
    password   VARCHAR(250) DEFAULT NULL,
    salt       VARCHAR(50)  DEFAULT NULL
);

DROP TABLE IF EXISTS city CASCADE;

CREATE TABLE city
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    country     VARCHAR(50)  NOT NULL,
    description VARCHAR(1000) DEFAULT NULL,
    version     INT           DEFAULT 0
);

ALTER TABLE city
    ADD CONSTRAINT UC_country_name UNIQUE (country, name);

CREATE INDEX idx_city_name ON city (name);

DROP TABLE IF EXISTS user_role;

CREATE TABLE user_role
(
    user_id INT,
    role    VARCHAR(20)
);

DROP TABLE IF EXISTS comment;

CREATE TABLE comment
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(1000) NOT NULL,
    created     TIMESTAMP     NOT NULL,
    modified    TIMESTAMP     NOT NULL,
    city_id     INT,
    FOREIGN KEY (city_id) REFERENCES city (id)
);

DROP TABLE IF EXISTS airport;

CREATE TABLE airport
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    city_id   INT,
    iata      VARCHAR(3),
    icao      VARCHAR(4),
    latitude  DOUBLE      NOT NULL,
    longitude DOUBLE      NOT NULL,
    altitude  DOUBLE      NOT NULL,
    timezone  DOUBLE,
    dst       VARCHAR(10),
    tz_time   VARCHAR(30),
    type      VARCHAR(30) NOT NULL,
    source    VARCHAR(30) NOT NULL,
    FOREIGN KEY (city_id) REFERENCES city (id)
);

SET FOREIGN_KEY_CHECKS = 1;