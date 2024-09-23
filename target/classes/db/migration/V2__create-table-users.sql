CREATE TABLE users(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cpf VARCHAR(13) NOT NULL UNIQUE,
    name VARCHAR(80) NOT NULL,
    role_id INT,
    salary DECIMAL(9,2) NOT NULL,
    ingressed_at TIMESTAMP,
    fantasy_name VARCHAR(80) NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);


