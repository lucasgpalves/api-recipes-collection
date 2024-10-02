CREATE TABLE goals(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(40) NOT NULL,
    amount INT NOT NULL,
    starts_at TIMESTAMP NOT NULL,
    description VARCHAR(255),
    role_id INT NOT NULL,

    FOREIGN KEY (role_id) REFERENCES roles(id)
);