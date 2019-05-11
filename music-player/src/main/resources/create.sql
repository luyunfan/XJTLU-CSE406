CREATE DATABASE IF NOT EXISTS musicPlayer;

USE musicPlayer;

CREATE TABLE IF NOT EXISTS user
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(50) NOT NULL
) ENGINE = innodb
  default charset = utf8mb4;

CREATE TABLE IF NOT EXISTS music
(
    id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    path VARCHAR(50) NOT NULL
) ENGINE = innodb
  default charset = utf8mb4;

CREATE TABLE IF NOT EXISTS comment
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    musicId  INTEGER     NOT NULL,
    username VARCHAR(20) NOT NULL,
    content  VARCHAR(50) NOT NULL,
    date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE = innodb
  default charset = utf8mb4;
