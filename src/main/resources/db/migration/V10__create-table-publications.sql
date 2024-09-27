CREATE TABLE publications(
    book_id BIGINT NOT NULL,
    recipe_id BIGINT NOT NULL,

    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (recipe_id) REFERENCES recipes(id)
);