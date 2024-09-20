CREATE TABLE recipes(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    name VARCHAR(120) NOT NULL,
    category_id INT,
    created_at TIMESTAMP,
    preparation_method CLOB NOT NULL,
    portions DOUBLE NOT NULL,
    preparation_time INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    is_published BOOLEAN,
    is_rated BOOLEAN,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);