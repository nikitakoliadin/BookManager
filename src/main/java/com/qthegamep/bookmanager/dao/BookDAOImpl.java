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
        } finally {
            SessionUtil.closeConnection();
        }

        log.info("Preparing to execute CREATE CRUD operation was done successful");
    }

    @Override
    public void addAll(List<? extends Book> books) throws SQLException {

    }

    @Override
    public Book getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Book> getByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<Book> getByAuthor(String author) throws SQLException {
        return null;
    }

    @Override
    public List<Book> getByPrintYear(int printYear) throws SQLException {
        return null;
    }

    @Override
    public List<Book> getByIsRead(boolean isRead) throws SQLException {
        return null;
    }

    @Override
    public List<Book> getAll() throws SQLException {
        return null;
    }

    @Override
    public void update(Book book) throws SQLException {

    }

    @Override
    public void updateAll(List<? extends Book> books) throws SQLException {

    }

    @Override
    public void remove(Book book) throws SQLException {

    }

    @Override
    public void removeAll(List<? extends Book> books) throws SQLException {

    }
}
