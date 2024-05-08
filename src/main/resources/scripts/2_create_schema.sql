USE cs157a_final;

CREATE TABLE users (
    `username` VARCHAR(64),
    `password` BINARY(72) NOT NULL,
    `email` VARCHAR(255) UNIQUE,
    `first_name` VARCHAR(64),
    `last_name` VARCHAR(64),
    `active` TINYINT(1),
    `dob` DATE,
    PRIMARY KEY (`username`),
    INDEX idx_first_last_name (`first_name`, `last_name`)
);

CREATE TABLE user_roles (
    `role_id` INT AUTO_INCREMENT,
    `username` VARCHAR(64) NOT NULL,
    `role` VARCHAR(64) NOT NULL,
    PRIMARY KEY (`role_id`),
    FOREIGN KEY (`username`) REFERENCES users(`username`) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_username_role (`username`, `role`)
);

CREATE TABLE authors (
    `author_id` INT AUTO_INCREMENT,
    `first_name` VARCHAR(64) NOT NULL,
    `last_name` VARCHAR(64) NOT NULL,
    `biography` VARCHAR(512) NOT NULL,
    PRIMARY KEY (`author_id`),
    INDEX idx_author_name (`first_name`, `last_name`)
);

CREATE TABLE genres (
    `genre_name` VARCHAR(64) NOT NULL,
    `overview` VARCHAR(512) NOT NULL,
    PRIMARY KEY (`genre_name`)
);

CREATE TABLE books (
    `isbn` CHAR(13),
    `title` VARCHAR(64) NOT NULL,
    `author_id` INT NOT NULL,
    `publish_date` DATE NOT NULL,
    `description` VARCHAR(512),
    PRIMARY KEY (`isbn`),
    FOREIGN KEY (`author_id`) REFERENCES authors(`author_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_title (`title`),
    INDEX idx_author (`author_id`),
    INDEX idx_author_title (`author_id`, `title`)
);

CREATE TABLE reviews (
    `review_id` INT AUTO_INCREMENT,
    `content` VARCHAR(512) NOT NULL,
    `username` VARCHAR(64) NOT NULL,
    `publish_date` DATE NOT NULL,
    `isbn` CHAR(13) NOT NULL,
    PRIMARY KEY (`review_id`),
    FOREIGN KEY (`username`) REFERENCES users(`username`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`isbn`) REFERENCES books(`isbn`) ON DELETE CASCADE,
    INDEX idx_username (`username`),
    INDEX idx_isbn (`isbn`)
);

CREATE TABLE book_checkouts (
    `checkout_id` INT AUTO_INCREMENT,
    `username` VARCHAR(64) NOT NULL,
    `isbn` CHAR(13) NOT NULL,
    `checkout_date` DATE NOT NULL,
    PRIMARY KEY (`checkout_id`),
    FOREIGN KEY (`username`) REFERENCES users(`username`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`isbn`) REFERENCES books(`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_username_isbn (`username`, `isbn`),
    INDEX idx_isbn_checkout_date (`isbn`, `checkout_date`)
);

CREATE TABLE user_favorites_author (
    `username` VARCHAR(64),
    `author_id` INT,
    PRIMARY KEY (`username`, `author_id`),
    FOREIGN KEY (`username`) REFERENCES users(`username`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`author_id`) REFERENCES authors(`author_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_likes_genres (
    `username` VARCHAR(64),
    `genre_name` VARCHAR(64),
    PRIMARY KEY (`username`, `genre_name`),
    FOREIGN KEY (`username`) REFERENCES users(`username`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`genre_name`) REFERENCES genres(`genre_name`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_rates_book (
    `username` VARCHAR(64),
    `isbn` CHAR(13),
    `stars` TINYINT,
    PRIMARY KEY (`username`, `isbn`),
    FOREIGN KEY (`username`) REFERENCES users(`username`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`isbn`) REFERENCES books(`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_isbn_stars (`isbn`, `stars`)
);

CREATE TABLE book_is_genre (
    `isbn` CHAR(13),
    `genre_name` VARCHAR(64),
    PRIMARY KEY (`isbn`, `genre_name`),
    FOREIGN KEY (`isbn`) REFERENCES books(`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`genre_name`) REFERENCES genres(`genre_name`) ON DELETE CASCADE ON UPDATE CASCADE
);