package com.qthegamep.bookmanager.dao;

import com.qthegamep.bookmanager.entity.Book;
import com.qthegamep.bookmanager.testhelper.rule.Rules;
import com.qthegamep.bookmanager.util.SessionUtil;

import lombok.val;
import org.junit.*;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;

import java.sql.Connection;
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

    private Connection connection;

    private BookDAO bookDAO;

    private Book firstBook;
    private Book secondBook;

    private List<Book> books;

    @Before
    public void setUp() throws SQLException {
        connection = SessionUtil.openConnection();

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

        books = List.of(firstBook, secondBook);
    }

    @After
    public void tearDown() throws SQLException {
        SessionUtil.closeConnection();
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

        var allEntitiesFromDatabase = getAllEntitiesFromDatabase();

        assertThat(allEntitiesFromDatabase).isNotNull().hasSize(1);
        assertThat(allEntitiesFromDatabase).contains(firstBook);

        bookDAO.add(secondBook);

        allEntitiesFromDatabase = getAllEntitiesFromDatabase();

        assertThat(allEntitiesFromDatabase).isNotNull().hasSize(2);
        assertThat(allEntitiesFromDatabase).contains(firstBook, secondBook);
    }

    @Test
    public void shouldBeOpenConnectionAfterAddMethod() throws SQLException {
        bookDAO.add(firstBook);

        assertThat(connection.isClosed()).isFalse();
    }

    @Test
    public void shouldAddAllEntitiesToTheDatabaseCorrectly() throws SQLException {
        bookDAO.addAll(books);

        var allEntitiesFromDatabase = getAllEntitiesFromDatabase();

        assertThat(allEntitiesFromDatabase).isNotNull().hasSize(2);
        assertThat(allEntitiesFromDatabase).contains(firstBook, secondBook);

        bookDAO.addAll(books);

        allEntitiesFromDatabase = getAllEntitiesFromDatabase();

        val thirdBook = firstBook;
        val fourthBook = secondBook;

        thirdBook.setId(3);
        fourthBook.setId(4);

        assertThat(allEntitiesFromDatabase).isNotNull().hasSize(4);
        assertThat(allEntitiesFromDatabase).contains(firstBook, secondBook, thirdBook, fourthBook);
    }

    @Test
    public void shouldBeOpenConnectionAfterAddAllMethod() throws SQLException {
        bookDAO.addAll(books);

        assertThat(connection.isClosed()).isFalse();
    }

    @Test
    public void shouldGetByIdEntityFromTheDatabaseCorrectly() throws SQLException {
        addAllEntitiesToTheDatabase(books);

        val firstBook = bookDAO.getById(1);
        val secondBook = bookDAO.getById(2);

        assertThat(firstBook).isEqualTo(this.firstBook);
        assertThat(secondBook).isEqualTo(this.secondBook);

        val allEntitiesFromDatabase = getAllEntitiesFromDatabase();

        assertThat(allEntitiesFromDatabase).hasSize(2).contains(this.firstBook, this.secondBook);
    }

    @Test
    public void shouldGetByIdMethodReturnEmptyEntityIfIdNotExist() throws SQLException {
        val emptyBook = bookDAO.getById(1);

        val expectedEmptyBook = new Book();

        assertThat(emptyBook).isEqualTo(expectedEmptyBook);
    }

    @Test
    public void shouldBeOpenConnectionAfterGetByIdMethod() throws SQLException {
        bookDAO.getById(1);

        assertThat(connection.isClosed()).isFalse();
    }

    @Test
    public void shouldGetByNameEntitiesFromTheDatabaseCorrectly() throws SQLException {
        addAllEntitiesToTheDatabase(books);

        val firstListBooks = bookDAO.getByName("test firstBook");

        assertThat(firstListBooks).isNotNull().hasSize(1).contains(firstBook);

        addAllEntitiesToTheDatabase(books);

        val secondListBooks = bookDAO.getByName("test secondBook");

        assertThat(secondListBooks).isNotNull().hasSize(2).contains(secondBook);
    }

    @Test
    public void shouldGetByNameMethodReturnEmptyEntitiesListCorrectly() throws SQLException {
        val books = bookDAO.getByName("test firstBook");

        assertThat(books).isNotNull().hasSize(0);
    }

    @Test
    public void shouldBeOpenConnectionAfterGetByNameMethod() throws SQLException {
        bookDAO.getByName("test firstBook");

        assertThat(connection.isClosed()).isFalse();
    }

    @Test
    public void shouldGetByAuthorEntitiesFromTheDatabaseCorrectly() throws SQLException {
        addAllEntitiesToTheDatabase(books);

        val firstListBooks = bookDAO.getByAuthor("test firstAuthor");

        assertThat(firstListBooks).isNotNull().hasSize(1).contains(firstBook);

        addAllEntitiesToTheDatabase(books);

        val secondListBooks = bookDAO.getByAuthor("test secondAuthor");

        assertThat(secondListBooks).isNotNull().hasSize(2).contains(secondBook);
    }

    @Test
    public void shouldGetByAuthorMethodReturnEmptyEntitiesListCorrectly() throws SQLException {
        val books = bookDAO.getByAuthor("test firstAuthor");

        assertThat(books).isNotNull().hasSize(0);
    }

    @Test
    public void shouldBeOpenConnectionAfterGetByAuthorMethod() throws SQLException {
        bookDAO.getByAuthor("test firstAuthor");

        assertThat(connection.isClosed()).isFalse();
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenCallAddMethodWithNullParameter() {
        assertThatNullPointerException().isThrownBy(
                () -> bookDAO.add(null)
        ).withMessage(null);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenCallAddAllMethodWithNullParameter() {
        assertThatNullPointerException().isThrownBy(
                () -> bookDAO.addAll(null)
        ).withMessage(null);
    }

    private List<Book> getAllEntitiesFromDatabase() throws SQLException {
        val bookList = new ArrayList<Book>();

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
        }

        return bookList;
    }

    private void addAllEntitiesToTheDatabase(List<? extends Book> books) throws SQLException {
        val sql = "INSERT INTO BOOKS (NAME, AUTHOR, PRINT_YEAR, IS_READ) VALUES (?, ?, ?, ?);";

        try (val preparedStatement = connection.prepareStatement(sql)) {
            for (val book : books) {
                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setInt(3, book.getPrintYear());
                preparedStatement.setBoolean(4, book.isRead());

                preparedStatement.executeUpdate();
            }
        }
    }
}
