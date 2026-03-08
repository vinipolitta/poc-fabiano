CREATE TABLE clients (

                         id BIGINT AUTO_INCREMENT PRIMARY KEY,

                         name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         phone VARCHAR(50),
                         company VARCHAR(255),
                         notes TEXT,

                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

);