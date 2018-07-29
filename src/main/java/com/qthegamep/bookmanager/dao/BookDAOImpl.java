package com.qthegamep.bookmanager.dao;

import com.qthegamep.bookmanager.entity.Book;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

/**
 * This class is DAO that implements all standard CRUD operations.
 */
@Slf4j
public class BookDAOImpl implements BookDAO {

    @Override
    public void add(Book book) throws SQLException {

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
