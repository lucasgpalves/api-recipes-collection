CREATE TABLE job_references (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    starts_at DATE NOT NULL,
    ends_at DATE NOT NULL,
    contact_number VARCHAR(20),
    restaurant_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
)