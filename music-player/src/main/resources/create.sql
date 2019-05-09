CREATE DATABASE IF NOT EXISTS musicPlayer;

USE musicPlayer;

CREATE TABLE IF NOT EXISTS user
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(50) NOT NULL

) ENGINE = innodb default charset = utf8mb4;