INSERT INTO roles 
    VALUES ('ADMIN');

INSERT INTO user (cpf, name, role_id, salary, ingressed_at)
    VALUES ('123456789', 'Admin User', 1, 5000.00, CURRENT_TIMESTAMP);