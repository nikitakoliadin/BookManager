package com.qthegamep.bookmanager.service;

import com.qthegamep.bookmanager.dao.BookDAO;
import com.qthegamep.bookmanager.dao.BookDAOImpl;
import com.qthegamep.bookmanager.entity.Book;
import com.qthegamep.bookmanager.testhelper.rule.Rules;
import com.qthegamep.bookmanager.util.SessionUtil;

import lombok.val;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @ClassRule
    public static ExternalResource summaryRule = Rules.SUMMARY_RULE;

    @Rule
    public Stopwatch stopwatchRule = Rules.STOPWATCH_RULE;
    @Rule
    public ExternalResource resetDatabaseRule = Rules.RESET_DATABASE_RULE;

    private BookService bookService;
    private BookService bookServiceWithMock;

    private BookDAO bookDAO;
    @Mock
    private BookDAO bookDAOMock;

    private Book firstBook;
    private Book secondBook;

    private List<Book> books;

    @Before
    public void setUp() {
        bookService = new BookServiceImpl();
        bookServiceWithMock = new BookServiceImpl();

        bookDAO = new BookDAOImpl();

        ((BookServiceImpl) bookService).setBookDAO(bookDAO);
        ((BookServiceImpl) bookServiceWithMock).setBookDAO(bookDAOMock);

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
        assertThat(bookService).isNotNull();
        assertThat(bookServiceWithMock).isNotNull();
    }

    @Test
    public void shouldImplementsBookServiceInterface() {
        assertThat(bookService).isInstanceOf(BookService.class);
        assertThat(bookServiceWithMock).isInstanceOf(BookService.class);
    }

    @Test
    public void shouldBeEmptyDatabaseBeforeEachTest() throws SQLException {
        val allBooks = bookDAO.getAll();

        assertThat(allBooks).isNotNull().isEmpty();
    }

    @Test
    public void shouldGetAndSetBookDAO() {
        val newBookDAO = new BookDAOImpl();

        ((BookServiceImpl) bookService).setBookDAO(newBookDAO);

        assertThat(((BookServiceImpl) bookService).getBookDAO()).isNotNull().isEqualTo(newBookDAO);
    }

    @Test
    public void shouldRemoveAllBooksCorrectly() throws SQLException {
        bookDAO.addAll(books);

        bookService.removeAll(books);

        val allBooks = bookDAO.getAll();

        assertThat(allBooks).isNotNull().isEmpty();
    }

    @Test
    public void shouldCallRemoveAllMethodCorrectly() throws SQLException {
        bookServiceWithMock.removeAll(books);

        verify(bookDAOMock, times(1)).removeAll(books);

        verifyNoMoreInteractions(bookDAOMock);
    }
}
