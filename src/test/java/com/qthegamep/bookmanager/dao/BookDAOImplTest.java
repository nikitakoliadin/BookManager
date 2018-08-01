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

    private Book firstBook;
    private Book secondBook;

    @Before
    public void setUp() {
        bookDAO = new BookDAOImpl();

        firstBook = new Book();

        firstBook.setId(1);
        firstBook.setName("test firstBook");
        firstBook.setAuthor("test firstAuthor");
        firstBook.setPrintYear(2000);
        firstBook.setRead(false);

        secondBook = new Book();

        secondBook.setId(2);
        secondBook.setName("test secondBook");
        secondBook.setAuthor("test secondAuthor");
        secondBook.setPrintYear(2010);
        secondBook.setRead(true);
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
        bookDAO.add(firstBook);

        var getBooks = getAllEntitiesFromDatabase();

        assertThat(getBooks).isNotNull().hasSize(1);
        assertThat(getBooks).contains(firstBook);

        bookDAO.add(secondBook);

        getBooks = getAllEntitiesFromDatabase();

        assertThat(getBooks).isNotNull().hasSize(2);
        assertThat(getBooks).contains(firstBook, secondBook);
    }

    @Test
    public void shouldAddEntitiesToTheDatabaseCorrectly() throws SQLException {
        val books = List.of(firstBook, secondBook);

        bookDAO.addAll(books);

        var getBooks = getAllEntitiesFromDatabase();

        assertThat(getBooks).isNotNull().hasSize(2);
        assertThat(getBooks).contains(firstBook, secondBook);

        bookDAO.addAll(books);

        getBooks = getAllEntitiesFromDatabase();

        val thirdBook = firstBook;
        val fourthBook = secondBook;

        thirdBook.setId(3);
        fourthBook.setId(4);

        assertThat(getBooks).isNotNull().hasSize(4);
        assertThat(getBooks).contains(firstBook, secondBook, thirdBook, fourthBook);
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
