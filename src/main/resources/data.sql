CREATE TABLE IF NOT EXISTS insurance (
                                         id BIGSERIAL PRIMARY KEY,
                                         policy VARCHAR(255) NOT NULL,
    customer VARCHAR(255) NOT NULL,
    claim VARCHAR(255),
    payment DOUBLE PRECISION,
    coverage VARCHAR(255)
    );

-- Insert sample data
INSERT INTO insurance (policy, customer, claim, payment, coverage) VALUES
                                                                       ('POL001', 'John Doe', 'Car Accident', 1500.00, 'Full Coverage'),
                                                                       ('POL002', 'Jane Smith', 'Theft', 2500.50, 'Basic Coverage'),
                                                                       ('POL003', 'Bob Johnson', 'Fire Damage', 1800.75, 'Premium Coverage'),
                                                                       ('POL004', 'Alice Brown', 'Flood', 3200.00, 'Comprehensive'),
                                                                       ('POL005', 'Charlie Wilson', 'Medical', 1200.25, 'Health Insurance');

-- Create indexes for better performance
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS user_roles (
                                          user_id BIGINT NOT NULL,
                                          role VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    PRIMARY KEY (user_id, role)
    );