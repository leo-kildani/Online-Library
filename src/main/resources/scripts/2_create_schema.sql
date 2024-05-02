USE cs157a_final;

CREATE TABLE users (
    `username` VARCHAR(64),
    `password` BINARY(72) NOT NULL,
    `email` VARCHAR(255) UNIQUE,
    `first_name` VARCHAR(64),
    `last_name` VARCHAR(64),
    `active` TINYINT(1),
    `dob` DATE,
    PRIMARY KEY (`username`)
);

CREATE TABLE user_roles (
    `role_id` INT AUTO_INCREMENT,
    `username` VARCHAR(64) NOT NULL,
    `role` VARCHAR(64) NOT NULL,
    PRIMARY KEY (`role_id`),
    FOREIGN KEY (`username`) REFERENCES users(`username`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE authors (
    `author_id` INT AUTO_INCREMENT,
    `first_name` VARCHAR(64) NOT NULL,
    `last_name` VARCHAR(64) NOT NULL,
    `biography` VARCHAR(512) NOT NULL,
    PRIMARY KEY (`author_id`)
);

CREATE TABLE genres (
    `genre_name` VARCHAR(64) NOT NULL,
    `overview` VARCHAR(512) NOT NULL,
    PRIMARY KEY (`genre_name`)
);

CREATE TABLE subgenres (
    `genre_name` VARCHAR(64),
    `subgenre_name` VARCHAR(64) NOT NULL,
    PRIMARY KEY (`genre_name`, `subgenre_name`),
    FOREIGN KEY (`genre_name`) REFERENCES genres(`genre_name`) ON DELETE CASCADE,
    INDEX idx_genre_subgenre (`genre_name`, `subgenre_name`)
);

CREATE TABLE books (
    `isbn` CHAR(13),
    `title` VARCHAR(64) NOT NULL,
    `author_id` INT NOT NULL,
    `publish_date` DATE NOT NULL,
    `genre_name` VARCHAR(64) NOT NULL,
    PRIMARY KEY (`isbn`),
    FOREIGN KEY (`author_id`) REFERENCES authors(`author_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`genre_name`) REFERENCES genres(`genre_name`) ON DELETE RESTRICT
);

CREATE TABLE reviews (
    `review_id` INT AUTO_INCREMENT,
    `content` VARCHAR(512) NOT NULL,
    `poster_username` VARCHAR(64) NOT NULL,
    `publish_date` DATE NOT NULL,
    `isbn` CHAR(13) NOT NULL,
    PRIMARY KEY (`review_id`),
    FOREIGN KEY (`poster_username`) REFERENCES users(`username`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`isbn`) REFERENCES books(`isbn`) ON DELETE CASCADE
);

CREATE TABLE user_favorites_author (
    `username` VARCHAR(64),
    `author_id` INT,
    PRIMARY KEY (`username`, `author_id`)
);

CREATE TABLE user_likes_genre (
    `username` VARCHAR(64),
    `genre_name` VARCHAR(64),
    PRIMARY KEY (`username`, `genre_name`)
);

CREATE TABLE book_is_subgenre (
    `isbn` CHAR(13),
    `subgenre_name` VARCHAR(64),
    PRIMARY KEY (`isbn`, `subgenre_name`)
);