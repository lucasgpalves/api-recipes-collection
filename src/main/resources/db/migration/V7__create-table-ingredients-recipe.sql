CREATE TABLE ingredients_recipe(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    amount DOUBLE NOT NULL,
    recipe_id BIGINT NOT NULL,
    ingredient_id INT NOT NULL,
    measurement_id INT NOT NULL,

    FOREIGN KEY (recipe_id) REFERENCES recipes(id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredients(id),
    FOREIGN KEY (measurement_id) REFERENCES measurements(id)

);