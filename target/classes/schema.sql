DROP TABLE IF EXISTS confirmation_token ;
DROP TABLE IF EXISTS users ;
DROP TABLE IF EXISTS app_user ;

CREATE TABLE app_user (
    id INT IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255),
    enabled BIT
);

CREATE TABLE confirmation_token (
    id INT IDENTITY PRIMARY KEY,
    confirmed_at date,
    created_at date,
    expires_at date,
    token VARCHAR(50),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES app_user(id)

);



