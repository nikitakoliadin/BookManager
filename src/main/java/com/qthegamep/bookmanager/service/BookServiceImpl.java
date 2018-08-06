package com.qthegamep.bookmanager.service;

import com.qthegamep.bookmanager.dao.BookDAO;
import com.qthegamep.bookmanager.entity.Book;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

/**
 * This class is book service implementation.
 */
@Slf4j
public class BookServiceImpl implements BookService {

    @Getter
    @Setter
    private BookDAO bookDAO;

    /**
     * This service method implements adding book entity object to the database.
     * If the entity has id and this entity already exists in the database entity will be updated.
     *
     * @param book is the entity object that will be added to the database.
     * @throws SQLException of work with the database.
     */
    @Override
    public void add(Book book) throws SQLException {

    }

    /**
     * This service method implements adding list of books entities objects to the database.
     * If any entity has id and this entity already exists in the database entity will be updated.
     *
     * @param books is the list of entities objects that will be added to the database.
     * @throws SQLException of work with the database.
     */
    @Override
    public void addAll(List<? extends Book> books) throws SQLException {

    }

    /**
     * This service method implements returning book entity object from the database by id.
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
     * This service method implements returning list of books entities objects from the database by name.
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
     * This service method implements returning list of books entities objects from the database by author.
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
     * This service method implements returning list of books entities objects from the database by print year.
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
     * This service method implements returning list of books entities objects from the database by is read.
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
     * This service method implements returning list of all books entities objects from the database.
     *
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    @Override
    public List<Book> getAll() throws SQLException {
        return null;
    }

    /**
     * This service method implements updating book entity object in the database.
     *
     * @param book is the new entity that will be added to the database instead of the old one.
     * @throws SQLException of work with the database.
     */
    @Override
    public void update(Book book) throws SQLException {

    }

    /**
     * This service method implements updating list of books entities objects in the database.
     *
     * @param books is the new entities that will be added to the database instead of the old ones.
     * @throws SQLException of work with the database.
     */
    @Override
    public void updateAll(List<? extends Book> books) throws SQLException {

    }

    /**
     * This service method implements deleting book entity object from the database.
     *
     * @param book is the entity that will be deleted from the database.
     * @throws SQLException of work with the database.
     */
    @Override
    public void remove(Book book) throws SQLException {

    }

    /**
     * This service method implements deleting list of books entities objects from the database.
     *
     * @param books is the entities that will be deleted from the database.
     * @throws SQLException of work with the database.
     */
    @Override
    public void removeAll(List<? extends Book> books) throws SQLException {
        log.info("Preparing to remove all books");

        bookDAO.removeAll(books);
    }
}
