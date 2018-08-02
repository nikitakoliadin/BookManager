package com.qthegamep.bookmanager.dao;

import com.qthegamep.bookmanager.entity.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface is a DAO. It has all the standard CRUD operations.
 */
public interface BookDAO {

    /**
     * This method add book entity object to the database.
     *
     * @param book is the entity object that will be added to the database.
     * @throws SQLException of work with the database.
     */
    void add(Book book) throws SQLException;

    /**
     * This method add books entities objects to the database.
     *
     * @param books is the list of entities objects that will be added to the database.
     * @throws SQLException of work with the database.
     */
    void addAll(List<? extends Book> books) throws SQLException;

    /**
     * This method return book entity object from the database by id.
     *
     * @param id is the parameter by which the entity object will be returned.
     * @return book entity object.
     * @throws SQLException of work with the database.
     */
    Book getById(int id) throws SQLException;

    /**
     * This method return list of entities objects from the database by name.
     *
     * @param name is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    List<Book> getByName(String name) throws SQLException;

    /**
     * This method return list of entities objects from the database by author.
     *
     * @param author is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    List<Book> getByAuthor(String author) throws SQLException;

    /**
     * This method return list of entities objects from the database by print year.
     *
     * @param printYear is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    List<Book> getByPrintYear(int printYear) throws SQLException;

    /**
     * This method return list of entities objects from the database by is read.
     *
     * @param isRead is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    List<Book> getByIsRead(boolean isRead) throws SQLException;

    /**
     * This method return list of all entities objects from the database.
     *
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    List<Book> getAll() throws SQLException;

    /**
     * This method update entity in the database.
     *
     * @param book is the new entity that will be added to the database instead of the old one.
     * @throws SQLException of work with the database.
     */
    void update(Book book) throws SQLException;

    /**
     * This method update entities in the database.
     *
     * @param books is the new entities that will be added to the database instead of the old ones.
     * @throws SQLException of work with the database.
     */
    void updateAll(List<? extends Book> books) throws SQLException;

    /**
     * This method delete entity from the database.
     *
     * @param book is the entity that will be deleted from the database.
     * @throws SQLException of work with the database.
     */
    void remove(Book book) throws SQLException;

    /**
     * This method delete entities from the database.
     *
     * @param books is the entities that will be deleted from the database.
     * @throws SQLException of work with the database.
     */
    void removeAll(List<? extends Book> books) throws SQLException;
}
