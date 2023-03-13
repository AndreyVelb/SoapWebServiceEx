CREATE TABLE IF NOT EXISTS bank_clients
(
    id         uuid PRIMARY KEY,
    last_name  VARCHAR(40) NOT NULL,
    first_name VARCHAR(40) NOT NULL,
    login      VARCHAR(40) NOT NULL UNIQUE ,
    password   VARCHAR(40) NOT NULL
);
