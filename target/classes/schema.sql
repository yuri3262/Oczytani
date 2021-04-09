DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INT IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);

