package com.qthegamep.bookmanager.dao;

import com.qthegamep.bookmanager.entity.Book;
import com.qthegamep.bookmanager.util.SessionUtil;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is DAO that implements all standard CRUD operations.
 */
@Slf4j
public class BookDAOImpl implements BookDAO {

    /**
     * This method implements adding book entity object to the database.
     *
     * @param book is the entity object that will be added to the database.
     * @throws SQLException of work with the database.
     */
    @Override
    public void add(Book book) throws SQLException {
        log.info("Preparing to execute CREATE CRUD operation");

        val connection = SessionUtil.openConnection();

        val sql = "INSERT INTO BOOKS (NAME, AUTHOR, PRINT_YEAR, IS_READ) VALUES (?, ?, ?, ?);";
        log.info("SQL query: [{}]", sql);

        log.info("Preparing to create prepared statement");
        try (val preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query");

            log.info("Entity to add: NAME = {}, AUTHOR = {}, PRINT_YEAR  = {}, IS_READ = {}",
                    book.getName(),
                    book.getAuthor(),
                    book.getPrintYear(),
                    book.isRead()
            );

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPrintYear());
            preparedStatement.setBoolean(4, book.isRead());
            log.info("Preparing sql query was done successful! Preparing to add entity to the database");

            preparedStatement.executeUpdate();
            log.info("Preparing to add entity to the database was done successful");
        }

        log.info("Preparing to execute CREATE CRUD operation was done successful");
    }

    /**
     * This method implements adding books entities objects to the database.
     *
     * @param books is the list of entities objects that will be added to the database.
     * @throws SQLException of work with the database.
     */
    @Override
    public void addAll(List<? extends Book> books) throws SQLException {
        log.info("Preparing to execute CREATE CRUD operation");

        val connection = SessionUtil.openConnection();

        val sql = "INSERT INTO BOOKS (NAME, AUTHOR, PRINT_YEAR, IS_READ) VALUES (?, ?, ?, ?);";
        log.info("SQL query: [{}]", sql);

        log.info("Preparing to create prepared statement");
        try (val preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query for each entity");

            for (val book : books) {
                log.info("Entity to add: NAME = {}, AUTHOR = {}, PRINT_YEAR  = {}, IS_READ = {}",
                        book.getName(),
                        book.getAuthor(),
                        book.getPrintYear(),
                        book.isRead()
                );

                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setInt(3, book.getPrintYear());
                preparedStatement.setBoolean(4, book.isRead());
                log.info("Preparing sql query to this entity was done successful! Preparing to add entity to the database");

                preparedStatement.executeUpdate();
                log.info("Preparing to add entity to the database was done successful");
            }

            log.info("All entities was added to the database");
        }

        log.info("Preparing to execute CREATE CRUD operation was done successful");
    }

    /**
     * This method implements returning book entity object from the database by id.
     *
     * @param id is the parameter by which the entity object will be returned.
     * @return book entity object.
     * @throws SQLException of work with the database.
     */
    @Override
    public Book getById(int id) throws SQLException {
        log.info("Preparing to execute READ CRUD operation");

        val book = new Book();

        val connection = SessionUtil.openConnection();

        val sql = "SELECT * FROM BOOKS WHERE ID = ?;";
        log.info("SQL query: [{}]", sql);

        log.info("Preparing to create prepared statement");
        try (val preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query! ID = {}", id);

            preparedStatement.setInt(1, id);
            log.info("Preparing sql query was done successful! Preparing to get entity from the database by id");

            val resultSet = preparedStatement.executeQuery();
            log.info("Preparing to get entity from the database by id was done successful! Preparing to parse entity");

            if (resultSet.next()) {
                book.setId(resultSet.getInt("ID"));
                book.setName(resultSet.getString("NAME"));
                book.setAuthor(resultSet.getString("AUTHOR"));
                book.setPrintYear(resultSet.getInt("PRINT_YEAR"));
                book.setRead(resultSet.getBoolean("IS_READ"));
            } else {
                log.info("Will be returned empty entity");
            }

            log.info("Entity: ID = {}, NAME = {}, AUTHOR = {}, PRINT_YEAR  = {}, IS_READ = {}",
                    book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getPrintYear(),
                    book.isRead()
            );

            log.info("Preparing to parse entity was done successful");
        }

        log.info("Preparing to execute READ CRUD operation was done successful");

        return book;
    }

    /**
     * This method implements returning list of entities objects from the database by name.
     *
     * @param name is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    @Override
    public List<Book> getByName(String name) throws SQLException {
        log.info("Preparing to execute READ CRUD operation");

        val books = new ArrayList<Book>();

        val connection = SessionUtil.openConnection();

        val sql = "SELECT * FROM BOOKS WHERE NAME = ?;";
        log.info("SQL query: [{}]", sql);

        log.info("Preparing to create prepared statement");
        try (val preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query! NAME = {}", name);

            preparedStatement.setString(1, name);
            log.info("Preparing sql query was done successful! Preparing to get entity from the database by name");

            val resultSet = preparedStatement.executeQuery();
            log.info("Preparing to get entity from the database by name was done successful! Preparing to parse entities");

            while (resultSet.next()) {
                val book = new Book();

                book.setId(resultSet.getInt("ID"));
                book.setName(resultSet.getString("NAME"));
                book.setAuthor(resultSet.getString("AUTHOR"));
                book.setPrintYear(resultSet.getInt("PRINT_YEAR"));
                book.setRead(resultSet.getBoolean("IS_READ"));

                books.add(book);

                log.info("Entity: ID = {}, NAME = {}, AUTHOR = {}, PRINT_YEAR  = {}, IS_READ = {}",
                        book.getId(),
                        book.getName(),
                        book.getAuthor(),
                        book.getPrintYear(),
                        book.isRead()
                );
            }

            log.info("Preparing to parse entities was done successful");
        }

        log.info("Preparing to execute READ CRUD operation was done successful");

        return books;
    }

    /**
     * This method implements returning list of entities objects from the database by author.
     *
     * @param author is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    @Override
    public List<Book> getByAuthor(String author) throws SQLException {
        return null;
    }

    /**
     * This method implements returning list of entities objects from the database by print year.
     *
     * @param printYear is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    @Override
    public List<Book> getByPrintYear(int printYear) throws SQLException {
        return null;
    }

    /**
     * This method implements returning list of entities objects from the database by is read.
     *
     * @param isRead is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    @Override
    public List<Book> getByIsRead(boolean isRead) throws SQLException {
        return null;
    }

    /**
     * This method implements returning list of all entities objects from the database.
     *
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    @Override
    public List<Book> getAll() throws SQLException {
        return null;
    }

    /**
     * This method implements updating entity in the database.
     *
     * @param book is the new entity that will be added to the database instead of the old one.
     * @throws SQLException of work with the database.
     */
    @Override
    public void update(Book book) throws SQLException {

    }

    /**
     * This method implements updating entities in the database.
     *
     * @param books is the new entities that will be added to the database instead of the old ones.
     * @throws SQLException of work with the database.
     */
    @Override
    public void updateAll(List<? extends Book> books) throws SQLException {

    }

    /**
     * This method implements deleting entity from the database.
     *
     * @param book is the entity that will be deleted from the database.
     * @throws SQLException of work with the database.
     */
    @Override
    public void remove(Book book) throws SQLException {

    }

    /**
     * This method implements deleting entities from the database.
     *
     * @param books is the entities that will be deleted from the database.
     * @throws SQLException of work with the database.
     */
    @Override
    public void removeAll(List<? extends Book> books) throws SQLException {

    }
}
