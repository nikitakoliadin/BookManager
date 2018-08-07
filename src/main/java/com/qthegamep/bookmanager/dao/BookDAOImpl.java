package com.qthegamep.bookmanager.dao;

import com.qthegamep.bookmanager.entity.Book;
import com.qthegamep.bookmanager.util.SessionUtil;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is DAO that implements all standard CRUD operations.
 */
@Slf4j
public class BookDAOImpl implements BookDAO {

    /**
     * This DAO method implements adding book entity object to the database.
     * This method is transactional.
     *
     * @param book is the entity object that will be added to the database.
     * @throws SQLException of work with the database.
     */
    @Override
    public void add(Book book) throws SQLException {
        log.info("Preparing to execute CREATE CRUD operation");

        val connection = SessionUtil.openConnection();

        SessionUtil.setAutoCommit(false);

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
            log.info("Preparing to add entity to the database was done successful! Preparing to commit");

            connection.commit();
            log.info("Preparing to commit was done successful");

            log.info("Entity was added to the database");
        } catch (Exception e) {
            log.info("Preparing to rollback");

            connection.rollback();
            log.info("Preparing to rollback was done successful! Exception message: [{}]",
                    e.getMessage(),
                    e
            );
        }

        log.info("Preparing to execute CREATE CRUD operation was done successful");
    }

    /**
     * This DAO method implements adding list of books entities objects to the database.
     * This method is transactional.
     * This method uses a batch for multiple queries.
     *
     * @param books is the list of entities objects that will be added to the database.
     * @throws SQLException of work with the database.
     */
    @Override
    public void addAll(List<? extends Book> books) throws SQLException {
        log.info("Preparing to execute CREATE CRUD operation");

        val connection = SessionUtil.openConnection();

        SessionUtil.setAutoCommit(false);

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
                log.info("Preparing sql query to this entity was done successful! Preparing to add query to the batch");

                preparedStatement.addBatch();
                log.info("Preparing to add query to the batch was done successful");
            }

            log.info("Preparing to execute batch");

            preparedStatement.executeBatch();
            log.info("Preparing to execute batch was done successful! Preparing to commit");

            connection.commit();
            log.info("Preparing to commit was done successful! Preparing to clear batch");

            preparedStatement.clearBatch();
            log.info("Preparing to clear batch was done successful");

            log.info("All entities was added to the database");
        } catch (Exception e) {
            log.info("Preparing to rollback");

            connection.rollback();
            log.info("Preparing to rollback was done successful! Exception message: [{}]",
                    e.getMessage(),
                    e
            );
        }

        log.info("Preparing to execute CREATE CRUD operation was done successful");
    }

    /**
     * This DAO method implements returning book entity object from the database by id.
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

        SessionUtil.setAutoCommit(true);

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

            log.info("Entity: ID = {}, NAME = {}, AUTHOR = {}, PRINT_YEAR  = {}, IS_READ = {} - was gotten",
                    book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getPrintYear(),
                    book.isRead()
            );

            log.info("Preparing to parse entity was done successful");

            log.info("Entity was gotten from the database by id");
        }

        log.info("Preparing to execute READ CRUD operation was done successful");

        return book;
    }

    /**
     * This DAO method implements returning list of books entities objects from the database by name.
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

        SessionUtil.setAutoCommit(true);

        val sql = "SELECT * FROM BOOKS WHERE NAME = ?;";
        log.info("SQL query: [{}]", sql);

        log.info("Preparing to create prepared statement");
        try (val preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query! NAME = {}", name);

            preparedStatement.setString(1, name);
            log.info("Preparing sql query was done successful! Preparing to get entities from the database by name");

            val resultSet = preparedStatement.executeQuery();
            log.info("Preparing to get entities from the database by name was done successful! Preparing to parse entities");

            loadEntitiesToListFromResultSet(books, resultSet);
            log.info("Preparing to parse entities was done successful");

            log.info("Entities was gotten from the database by name");
        }

        log.info("Preparing to execute READ CRUD operation was done successful");

        return books;
    }

    /**
     * This DAO method implements returning list of books entities objects from the database by author.
     *
     * @param author is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    @Override
    public List<Book> getByAuthor(String author) throws SQLException {
        log.info("Preparing to execute READ CRUD operation");

        val books = new ArrayList<Book>();

        val connection = SessionUtil.openConnection();

        SessionUtil.setAutoCommit(true);

        val sql = "SELECT * FROM BOOKS WHERE AUTHOR = ?;";
        log.info("SQL query: [{}]", sql);

        log.info("Preparing to create prepared statement");
        try (val preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query! AUTHOR = {}", author);

            preparedStatement.setString(1, author);
            log.info("Preparing sql query was done successful! Preparing to get entities from the database by author");

            val resultSet = preparedStatement.executeQuery();
            log.info("Preparing to get entities from the database by author was done successful! Preparing to parse entities");

            loadEntitiesToListFromResultSet(books, resultSet);
            log.info("Preparing to parse entities was done successful");

            log.info("Entities was gotten from the database by author");
        }

        log.info("Preparing to execute READ CRUD operation was done successful");

        return books;
    }

    /**
     * This DAO method implements returning list of books entities objects from the database by print year.
     *
     * @param printYear is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    @Override
    public List<Book> getByPrintYear(int printYear) throws SQLException {
        log.info("Preparing to execute READ CRUD operation");

        val books = new ArrayList<Book>();

        val connection = SessionUtil.openConnection();

        SessionUtil.setAutoCommit(true);

        val sql = "SELECT * FROM BOOKS WHERE PRINT_YEAR = ?;";
        log.info("SQL query: [{}]", sql);

        log.info("Preparing to create prepared statement");
        try (val preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query! PRINT_YEAR = {}", printYear);

            preparedStatement.setInt(1, printYear);
            log.info("Preparing sql query was done successful! Preparing to get entities from the database by print year");

            val resultSet = preparedStatement.executeQuery();
            log.info("Preparing to get entities from the database by print year was done successful! Preparing to parse entities");

            loadEntitiesToListFromResultSet(books, resultSet);
            log.info("Preparing to parse entities was done successful");

            log.info("Entities was gotten from the database by print year");
        }

        log.info("Preparing to execute READ CRUD operation was done successful");

        return books;
    }

    /**
     * This DAO method implements returning list of books entities objects from the database by is read.
     *
     * @param isRead is the parameter by which the list of entities objects will be returned.
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    @Override
    public List<Book> getByIsRead(boolean isRead) throws SQLException {
        log.info("Preparing to execute READ CRUD operation");

        val books = new ArrayList<Book>();

        val connection = SessionUtil.openConnection();

        SessionUtil.setAutoCommit(true);

        val sql = "SELECT * FROM BOOKS WHERE IS_READ = ?;";
        log.info("SQL query: [{}]", sql);

        log.info("Preparing to create prepared statement");
        try (val preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query! IS_READ = {}", isRead);

            preparedStatement.setBoolean(1, isRead);
            log.info("Preparing sql query was done successful! Preparing to get entities from the database by is read");

            val resultSet = preparedStatement.executeQuery();
            log.info("Preparing to get entities from the database by is read was done successful! Preparing to parse entities");

            loadEntitiesToListFromResultSet(books, resultSet);
            log.info("Preparing to parse entities was done successful");

            log.info("Entities was gotten from the database by is read");
        }

        log.info("Preparing to execute READ CRUD operation was done successful");

        return books;
    }

    /**
     * This DAO method implements returning list of all books entities objects from the database.
     *
     * @return list of books entities objects.
     * @throws SQLException of work with the database.
     */
    @Override
    public List<Book> getAll() throws SQLException {
        log.info("Preparing to execute READ CRUD operation");

        val books = new ArrayList<Book>();

        val connection = SessionUtil.openConnection();

        SessionUtil.setAutoCommit(true);

        val sql = "SELECT * FROM BOOKS;";
        log.info("SQL query: [{}]", sql);

        log.info("Preparing to create statement");
        try (val statement = connection.createStatement()) {
            log.info("Preparing to create statement was done successful! Preparing to get all entities from the database");

            val resultSet = statement.executeQuery(sql);
            log.info("Preparing to get all entities from the database by is read was done successful! Preparing to parse entities");

            loadEntitiesToListFromResultSet(books, resultSet);
            log.info("Preparing to parse entities was done successful");

            log.info("All entities was gotten from the database");
        }

        log.info("Preparing to execute READ CRUD operation was done successful");

        return books;
    }

    /**
     * This DAO method implements updating book entity object in the database.
     * This method is transactional.
     *
     * @param book is the new entity that will be added to the database instead of the old one.
     * @throws SQLException of work with the database.
     */
    @Override
    public void update(Book book) throws SQLException {
        log.info("Preparing to execute UPDATE CRUD operation");

        val connection = SessionUtil.openConnection();

        SessionUtil.setAutoCommit(false);

        val sql = "UPDATE BOOKS SET NAME = ?, AUTHOR = ?, PRINT_YEAR = ?, IS_READ = ? WHERE ID = ?;";
        log.info("SQL query: [{}]", sql);

        log.info("Preparing to create prepared statement");
        try (val preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query");

            log.info("Entity to update: ID = {}, NAME = {}, AUTHOR = {}, PRINT_YEAR  = {}, IS_READ = {}",
                    book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getPrintYear(),
                    book.isRead()
            );

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPrintYear());
            preparedStatement.setBoolean(4, book.isRead());
            preparedStatement.setInt(5, book.getId());
            log.info("Preparing sql query was done successful! Preparing to update entity in the database");

            preparedStatement.executeUpdate();
            log.info("Preparing to update entity in the database was done successful! Preparing to commit");

            connection.commit();
            log.info("Preparing to commit was done successful");

            log.info("Entity was updated in the database");
        } catch (Exception e) {
            log.info("Preparing to rollback");

            connection.rollback();
            log.info("Preparing to rollback was done successful! Exception message: [{}]",
                    e.getMessage(),
                    e
            );
        }

        log.info("Preparing to execute UPDATE CRUD operation was done successful");
    }

    /**
     * This DAO method implements updating list of books entities objects in the database.
     * This method is transactional.
     * It is used a batch for multiple queries.
     *
     * @param books is the new entities that will be added to the database instead of the old ones.
     * @throws SQLException of work with the database.
     */
    @Override
    public void updateAll(List<? extends Book> books) throws SQLException {
        log.info("Preparing to execute UPDATE CRUD operation");

        val connection = SessionUtil.openConnection();

        SessionUtil.setAutoCommit(false);

        val sql = "UPDATE BOOKS SET NAME = ?, AUTHOR = ?, PRINT_YEAR = ?, IS_READ = ? WHERE ID = ?;";
        log.info("SQL query: [{}]", sql);

        log.info("Preparing to create prepared statement");
        try (val preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query for each entity");

            for (val book : books) {
                log.info("Entity to update: ID = {}, NAME = {}, AUTHOR = {}, PRINT_YEAR  = {}, IS_READ = {}",
                        book.getId(),
                        book.getName(),
                        book.getAuthor(),
                        book.getPrintYear(),
                        book.isRead()
                );

                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setInt(3, book.getPrintYear());
                preparedStatement.setBoolean(4, book.isRead());
                preparedStatement.setInt(5, book.getId());
                log.info("Preparing sql query to this entity was done successful! Preparing to add query to the batch");

                preparedStatement.addBatch();
                log.info("Preparing to add query to the batch was done successful");
            }

            log.info("Preparing to execute batch");

            preparedStatement.executeBatch();
            log.info("Preparing to execute batch was done successful! Preparing to commit");

            connection.commit();
            log.info("Preparing to commit was done successful! Preparing to clear batch");

            preparedStatement.clearBatch();
            log.info("Preparing to clear batch was done successful");

            log.info("All entities was updated in the database");
        } catch (Exception e) {
            log.info("Preparing to rollback");

            connection.rollback();
            log.info("Preparing to rollback was done successful! Exception message: [{}]",
                    e.getMessage(),
                    e
            );
        }

        log.info("Preparing to execute UPDATE CRUD operation was done successful");
    }

    /**
     * This DAO method implements deleting book entity object from the database.
     *
     * @param book is the entity that will be deleted from the database.
     * @throws SQLException of work with the database.
     */
    @Override
    public void remove(Book book) throws SQLException {
        log.info("Preparing to execute DELETE CRUD operation");

        val connection = SessionUtil.openConnection();

        val sql = "DELETE FROM BOOKS WHERE ID = ?;";
        log.info("SQL query: [{}]", sql);

        try (val preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query");

            log.info("Entity to delete: ID = {}, NAME = {}, AUTHOR = {}, PRINT_YEAR  = {}, IS_READ = {}",
                    book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getPrintYear(),
                    book.isRead()
            );

            preparedStatement.setInt(1, book.getId());
            log.info("Preparing sql query was done successful! Preparing to delete entity from the database");

            preparedStatement.executeUpdate();
            log.info("Preparing to delete entity from the database was done successful");
        }

        log.info("Preparing to execute DELETE CRUD operation was done successful");
    }

    /**
     * This DAO method implements deleting list of books entities objects from the database.
     * It is used a batch for multiple queries.
     *
     * @param books is the entities that will be deleted from the database.
     * @throws SQLException of work with the database.
     */
    @Override
    public void removeAll(List<? extends Book> books) throws SQLException {
        log.info("Preparing to execute DELETE CRUD operation");

        val connection = SessionUtil.openConnection();

        val sql = "DELETE FROM BOOKS WHERE ID = ?;";
        log.info("SQL query: [{}]", sql);

        log.info("Preparing to create prepared statement");
        try (val preparedStatement = connection.prepareStatement(sql)) {
            log.info("Preparing to create prepared statement was done successful! Preparing sql query for each entity");

            for (val book : books) {
                log.info("Entity to delete: ID = {}, NAME = {}, AUTHOR = {}, PRINT_YEAR  = {}, IS_READ = {}",
                        book.getId(),
                        book.getName(),
                        book.getAuthor(),
                        book.getPrintYear(),
                        book.isRead()
                );

                preparedStatement.setInt(1, book.getId());
                log.info("Preparing sql query to this entity was done successful! Preparing to add query to the batch");

                preparedStatement.addBatch();
                log.info("Preparing to add query to the batch was done successful");
            }

            log.info("Preparing to execute batch");

            preparedStatement.executeBatch();
            log.info("Preparing to execute batch was done successful! Preparing to clear batch");

            preparedStatement.clearBatch();
            log.info("Preparing to clear batch was done successful");

            log.info("All entities was deleted from the database");
        }

        log.info("Preparing to execute DELETE CRUD operation was done successful");
    }

    private void loadEntitiesToListFromResultSet(ArrayList<? super Book> books, @NotNull ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            val book = new Book();

            book.setId(resultSet.getInt("ID"));
            book.setName(resultSet.getString("NAME"));
            book.setAuthor(resultSet.getString("AUTHOR"));
            book.setPrintYear(resultSet.getInt("PRINT_YEAR"));
            book.setRead(resultSet.getBoolean("IS_READ"));

            books.add(book);

            log.info("Entity: ID = {}, NAME = {}, AUTHOR = {}, PRINT_YEAR  = {}, IS_READ = {} - was gotten",
                    book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getPrintYear(),
                    book.isRead()
            );
        }
    }
}
