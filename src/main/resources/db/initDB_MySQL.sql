DROP TABLE IF EXISTS bookmanager.books;

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
