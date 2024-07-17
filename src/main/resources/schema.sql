CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       status VARCHAR(255),
                       INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE operations (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            type ENUM('ADDITION', 'SUBTRACTION', 'MULTIPLICATION', 'DIVISION', 'SQUARE_ROOT', 'RANDOM_STRING') NOT NULL,
                            cost DOUBLE PRECISION,
                            INDEX idx_type (type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE records (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         operation_id BIGINT NOT NULL,
                         user_id BIGINT NOT NULL,
                         amount DOUBLE PRECISION,
                         user_balance DOUBLE PRECISION,
                         operation_response VARCHAR(255),
                         date TIMESTAMP NOT NULL,
                         FOREIGN KEY (operation_id) REFERENCES operations(id),
                         FOREIGN KEY (user_id) REFERENCES users(id),
                         INDEX idx_operation_id (operation_id),
                         INDEX idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;