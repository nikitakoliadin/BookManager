DROP USER IF EXISTS 'dev'@'localhost';

CREATE USER 'dev'@'localhost'
  IDENTIFIED BY 'dev';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP ON *.* TO 'dev'@'localhost';

CREATE DATABASE IF NOT EXISTS `bookmanager`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE `bookmanager`;

DROP TABLE IF EXISTS `bookmanager`.`books`;

CREATE TABLE `books` (
  `id`        int(11)      NOT NULL AUTO_INCREMENT,
  `name`      varchar(100) NOT NULL,
  `author`    varchar(100) NOT NULL,
  `printYear` int(4)       NOT NULL,
  `isRead`    tinyint(1)   NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;
