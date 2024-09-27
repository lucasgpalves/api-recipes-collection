CREATE TABLE books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(40) NOT NULL,
    description VARCHAR(120),
    user_id BIGINT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id)
);