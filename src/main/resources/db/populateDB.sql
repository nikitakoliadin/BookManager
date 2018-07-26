DELETE
FROM PUBLIC.BOOKS;

INSERT INTO PUBLIC.BOOKS (NAME, AUTOR, PRINT_YEAR, IS_READ)
VALUES ('Thinking In Java 4 Edition', 'Bruce Eckel', 2017, TRUE),
       ('Pro Spring', 'Clarence Ho, Rob Harrop, Chris Schaefer', 2014, FALSE);
