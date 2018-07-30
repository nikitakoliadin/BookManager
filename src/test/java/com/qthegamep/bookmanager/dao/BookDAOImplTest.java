package com.qthegamep.bookmanager.dao;

import com.qthegamep.bookmanager.entity.Book;
import com.qthegamep.bookmanager.testhelper.rule.Rules;
import com.qthegamep.bookmanager.util.SessionUtil;

import lombok.val;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BookDAOImplTest {

    @ClassRule
    public static ExternalResource summaryRule = Rules.SUMMARY_RULE;

    @Rule
    public Stopwatch stopwatchRule = Rules.STOPWATCH_RULE;
    @Rule
    public ExternalResource resetDatabaseRule = Rules.RESET_DATABASE_RULE;

    private BookDAO bookDAO;
    private Book book;

    @Before
    public void setUp() {
        bookDAO = new BookDAOImpl();

        book = new Book();

        book.setId(1);
        book.setName("test book");
        book.setAuthor("test author");
        book.setPrintYear(2000);
        book.setRead(false);
    }

    @Test
    public void shouldCreateObjectWithNoArgsConstructor() {
        assertThat(bookDAO).isNotNull();
    }

    @Test
    public void shouldImplementsBookDAOInterface() {
        assertThat(bookDAO).isInstanceOf(BookDAO.class);
    }

    @Test
    public void shouldBeEmptyDatabaseBeforeEachTest() throws SQLException {
        val allEntitiesFromDatabase = getAllEntitiesFromDatabase();

        assertThat(allEntitiesFromDatabase).isEmpty();
    }

    @Test
    public void shouldAddEntityToTheDatabaseCorrectly() throws SQLException {
        bookDAO.add(book);

        val books = getAllEntitiesFromDatabase();

        assertThat(books).isNotNull().hasSize(1);
        assertThat(books).contains(book);
    }

    private List<Book> getAllEntitiesFromDatabase() throws SQLException {
        val bookList = new ArrayList<Book>();

        val connection = SessionUtil.openConnection();

        try (val statement = connection.createStatement();
             val resultSet = statement.executeQuery("SELECT * FROM BOOKS;")) {
            while (resultSet.next()) {
                val book = new Book();

                book.setId(resultSet.getInt("ID"));
                book.setName(resultSet.getString("NAME"));
                book.setAuthor(resultSet.getString("AUTHOR"));
                book.setPrintYear(resultSet.getInt("PRINT_YEAR"));
                book.setRead(resultSet.getBoolean("IS_READ"));

                bookList.add(book);
            }
        } finally {
            SessionUtil.closeConnection();
        }

        return bookList;
    }
}
