-- create_schema.sql

-- Create the database if it doesn't exist
DROP DATABASE IF EXISTS cs157a_final;
CREATE DATABASE cs157a_final;
ALTER DATABASE cs157a_final CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- Create a new user for the 'simplevault' schema
DROP USER IF EXISTS 'user'@'localhost';
CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';

-- Grant privileges to the user for the 'simplevault' schema
GRANT ALL PRIVILEGES
    ON cs157a_final.*
    TO 'user'@'localhost';

-- Flush privileges to apply changes
FLUSH PRIVILEGES;