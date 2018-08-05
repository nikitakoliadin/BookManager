package com.qthegamep.bookmanager.service;

import com.qthegamep.bookmanager.dao.BookDAO;
import com.qthegamep.bookmanager.dao.BookDAOImpl;
import com.qthegamep.bookmanager.testhelper.rule.Rules;
import com.qthegamep.bookmanager.util.SessionUtil;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

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

    @Before
    public void setUp() {
        bookService = new BookServiceImpl();
        bookServiceWithMock = new BookServiceImpl();

        bookDAO = new BookDAOImpl();

        ((BookServiceImpl) bookService).setBookDAO(bookDAO);
        ((BookServiceImpl) bookServiceWithMock).setBookDAO(bookDAOMock);
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
    public void shouldGetAndSetBookDAO() {
        ((BookServiceImpl) bookServiceWithMock).setBookDAO(bookDAO);

        assertThat(((BookServiceImpl) bookServiceWithMock).getBookDAO()).isNotNull().isEqualTo(bookDAO);
    }
}
