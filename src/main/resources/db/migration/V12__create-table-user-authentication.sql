CREATE TABLE user_authentication(
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id)
);