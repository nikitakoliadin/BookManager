package com.qthegamep.bookmanager.dao;

import com.qthegamep.bookmanager.entity.Book;
import com.qthegamep.bookmanager.util.SessionUtil;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query");

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPrintYear());
            preparedStatement.setBoolean(4, book.isRead());

            log.info("Preparing sql query was done successful! Preparing to add entity to the database");

            val count = preparedStatement.executeUpdate();
            log.info("Preparing to add entity to the database was done successful! Count: [{}]", count);
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
        return null;
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
        return null;
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
