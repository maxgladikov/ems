
CREATE SEQUENCE user_seq
    START WITH 1
    INCREMENT BY 3;

CREATE TABLE IF NOT EXISTS users(
    id BIGINT,
    username VARCHAR(45) NOT NULL UNIQUE,
    parole VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS authorities(
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(25) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS users_authorities(
    user_id BIGINT,
    authority_id BIGINT,
    PRIMARY KEY(user_id, authority_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (authority_id) REFERENCES authorities(id)
);
