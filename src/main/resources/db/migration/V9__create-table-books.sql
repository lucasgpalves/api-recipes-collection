CREATE TABLE books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(40) NOT NULL,
    isbn VARCHAR(20),
    description VARCHAR(120),
    user_id BIGINT NOT NULL,
    is_published BOOLEAN,

    FOREIGN KEY (user_id) REFERENCES users(id)
);