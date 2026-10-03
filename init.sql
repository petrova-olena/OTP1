-- Create database if it does not exist
CREATE DATABASE IF NOT EXISTS temperature_converter;

-- Use the database
USE temperature_converter;

-- Create temperature_unit table
CREATE TABLE IF NOT EXISTS temperature_unit (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  name VARCHAR(50) NOT NULL,
                                  symbol VARCHAR(10) NOT NULL
);

-- Create temperature_conversion table
CREATE TABLE IF NOT EXISTS temperature_conversion (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                source_unit_id INT NOT NULL,
                                target_unit_id INT NOT NULL,
                                original_value DOUBLE NOT NULL,
                                converted_value DOUBLE NOT NULL,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                FOREIGN KEY (source_unit_id) REFERENCES temperature_unit(id),

                                FOREIGN KEY (target_unit_id) REFERENCES temperature_unit(id)
);

INSERT IGNORE INTO temperature_unit(name, symbol)
VALUES
    ('Celsius', 'C'),
    ('Fahrenheit', 'F');