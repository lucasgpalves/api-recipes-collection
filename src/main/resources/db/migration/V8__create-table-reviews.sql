CREATE TABLE reviews(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    created_at TIMESTAMP,
    rating DECIMAL(3,2) NOT NULL,
    description VARCHAR(255),
    user_id BIGINT NOT NULL,
    recipe_id BIGINT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (recipe_id) REFERENCES recipes(id)
);