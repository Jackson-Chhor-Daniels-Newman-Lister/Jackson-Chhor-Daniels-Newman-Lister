USE dog_lister_db;
# DROP TABLE IF EXISTS user_ads;
# DROP TABLE IF EXISTS dog_breeds;
# DROP TABLE IF EXISTS dog_traits;
# DROP TABLE IF EXISTS users;
# DROP TABLE IF EXISTS ads;
# DROP TABLE IF EXISTS dogs;
# DROP TABLE IF EXISTS breeds;
# DROP TABLE IF EXISTS traits;

CREATE TABLE users
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE dogs
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT,
    playfulness ENUM('1','2','3','4','5'),
    socialization ENUM('1','2','3','4','5'),
    affection ENUM('1','2','3','4','5'),
    training ENUM('1','2','3','4','5'),
    PRIMARY KEY (id)
);

CREATE TABLE ads
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    short_description VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price INT,
    image VARCHAR(255),
    dog_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (dog_id) REFERENCES dogs(id),
    PRIMARY KEY (id)
);

CREATE TABLE breeds
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE traits
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_ads
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id INT UNSIGNED NOT NULL,
    ad_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (ad_id) REFERENCES ads(id),
    PRIMARY KEY (id)
);

CREATE TABLE dog_breeds
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    dog_id INT UNSIGNED NOT NULL,
    breed_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (dog_id) REFERENCES dogs(id),
    FOREIGN KEY (breed_id) REFERENCES breeds(id),
    PRIMARY KEY (id)
);

CREATE TABLE dog_traits
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    dog_id INT UNSIGNED NOT NULL,
    trait_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (dog_id) REFERENCES dogs(id),
    FOREIGN KEY (trait_id) REFERENCES traits(id),
    PRIMARY KEY (id)
);

