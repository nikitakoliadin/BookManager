package com.qthegamep.bookmanager.service;

import com.qthegamep.bookmanager.entity.Book;

import java.util.List;

/**
 * This interface is a service. It contains all the services that this application can do with the database.
 */
public interface BookService {

    /**
     * This service method should add book entity object to the database.
     * If the entity has id and this entity already exists in the database entity will be updated.
     *
     * @param book is the entity object that will be added to the database.
     */
    void add(Book book);

    /**
     * This service method should add list of books entities objects to the database.
     * If any entity has id and this entity already exists in the database entity will be updated.
     *
     * @param books is the list of entities objects that will be added to the database.
     */
    void addAll(List<? extends Book> books);

    /**
     * This service method should return book entity object from the database by id.
     *
     * @param id is the parameter by which the entity object will be returned.
     * @return book entity object.
     */
    Book getById(int id);

    /**
     * This service method should return list of books entities objects from the database by name.
     *
     * @param name is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     */
    List<Book> getByName(String name);

    /**
     * This service method should return list of books entities objects from the database by author.
     *
     * @param author is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     */
    List<Book> getByAuthor(String author);

    /**
     * This service method should return list of books entities objects from the database by print year.
     *
     * @param printYear is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     */
    List<Book> getByPrintYear(int printYear);

    /**
     * This service method should return list of books entities objects from the database by is read.
     *
     * @param isRead is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     */
    List<Book> getByIsRead(boolean isRead);

    /**
     * This service method should return list of all books entities objects from the database.
     *
     * @return list of books entities objects.
     */
    List<Book> getAll();

    /**
     * This service method should update book entity object in the database.
     *
     * @param book is the new entity that will be added to the database instead of the old one.
     */
    void update(Book book);

    /**
     * This service method should update list of books entities objects in the database.
     *
     * @param books is the new entities that will be added to the database instead of the old ones.
     */
    void updateAll(List<? extends Book> books);

    /**
     * This service method should delete book entity object from the database.
     *
     * @param book is the entity that will be deleted from the database.
     */
    void remove(Book book);

    /**
     * This service method should delete list of books entities objects from the database.
     *
     * @param books is the entities that will be deleted from the database.
     */
    void removeAll(List<? extends Book> books);
}
