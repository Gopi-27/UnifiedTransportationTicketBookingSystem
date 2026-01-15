---- USERS  
CREATE TABLE users ( 
    username VARCHAR2(50) PRIMARY KEY,
    password VARCHAR2(50) NOT NULL,
    role     VARCHAR2(10) CHECK (role = 'ADMIN' || role = 'USER')
); 

INSERT INTO users VALUES ('admin1', 'admin123', 'ADMIN');
COMMIT;

