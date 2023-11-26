CREATE TABLE products (id serial, title varchar(100), price int);

CREATE TABLE items (id serial, title varchar(100), cost int);

INSERT INTO items (title) VALUES ('box'), ('tree'), ('house');

CREATE TABLE users (
    id serial,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(80) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id serial,
    name VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users_roles (
    user_id int NOT NULL,
    role_id int NOT NULL,
    PRIMARY KEY (user_id, role_id),

    CONSTRAINT FK_USER_ID_01 FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
    REFERENCES roles (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO roles (name)
VALUES
('ROLE_USER'), ('ROLE_ADMIN');


INSERT INTO users (username, password, name, email)
VALUES
('olya', '$2a$10$stjdCgRBLyoN.R.RsIrLVuU1KoDuhEDTrvjFm//FYP7lWo2n7YCY2', 'olga', 'olga@ya.ru'),
('bob', '$2a$10$stjdCgRBLyoN.R.RsIrLVu4wRSQQYG4WH2f1wDOiXcS6u1.i5tBUe', 'bobik', 'bob@ya.ru');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(2, 1);

INSERT INTO products (title, price) VALUES ('молоко', 80), ('хлеб', 50);
