CREATE TABLE users (
    username VARCHAR2(50) PRIMARY KEY,
    password VARCHAR2(50),
    role     VARCHAR2(10)
);

INSERT INTO users VALUES ('admin1', 'admin123', 'ADMIN');

DELETE FROM users WHERE username = 'admin';
SELECT * FROM users;
COMMIT;
