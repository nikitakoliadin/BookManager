package com.qthegamep.bookmanager.model.entity;

import com.qthegamep.bookmanager.test.rule.Rules;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;

import static org.assertj.core.api.Assertions.*;

public class BookTest {

    @ClassRule
    public static ExternalResource summaryRule = Rules.SUMMARY_RULE;

    @Rule
    public Stopwatch stopwatchRule = Rules.STOPWATCH_RULE;

    private Book book;

    @Before
    public void setUp() {
        book = new Book();
    }

    @Test
    public void shouldCreateObjectNoArgsConstructor() {
        assertThat(book).isNotNull();
    }

    @Test
    public void shouldCreateObjectAllArgsConstructor() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        assertThat(book).isNotNull();
    }

    @Test
    public void shouldGetAndSetId() {
        int id = 1;

        book.setId(id);

        assertThat(book.getId()).isEqualTo(id);
    }

    @Test
    public void shouldGetAndSetName() {
        String name = "testBook";

        book.setName(name);

        assertThat(book.getName()).isEqualTo(name);
    }

    @Test
    public void shouldGetAndSetAuthor() {
        String author = "testAuthor";

        book.setAuthor(author);

        assertThat(book.getAuthor()).isEqualTo(author);
    }

    @Test
    public void shouldGetAndSetPrintYear() {
        int printYear = 2010;

        book.setPrintYear(printYear);

        assertThat(book.getPrintYear()).isEqualTo(printYear);
    }

    @Test
    public void shouldGetAndSetIsRead() {
        book.setRead(false);

        assertThat(book.isRead()).isEqualTo(false);
    }

    @Test
    public void shouldBeEquals() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        Book copyBook = new Book(1, "testBook", "testAuthor", 2010, false);

        assertThat(book).isEqualTo(copyBook);
    }

    @Test
    public void shouldBeEqualsWithNullName() {
        book = new Book(1, null, "testAuthor", 2010, false);

        Book copyBook = new Book(1, null, "testAuthor", 2010, false);

        assertThat(book).isEqualTo(copyBook);
    }

    @Test
    public void shouldBeEqualsOfCopyObject() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        Book copyBook = book;

        assertThat(book).isEqualTo(copyBook);
    }

    @Test
    public void shouldBeNotEqualsIfIdIsNotEquals() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        Book newBook = new Book(2, "testBook", "testAuthor", 2010, false);

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfNameNullIsNotEquals() {
        book = new Book(1, null, "testAuthor", 2010, false);

        Book newBook = new Book(1, "testBook", "testAuthor", 2010, false);

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfNameIsNotEquals() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        Book newBook = new Book(1, "newBook", "testAuthor", 2010, false);

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfAuthorNullIsNotEquals() {
        book = new Book(1, "testBook", null, 2010, false);

        Book newBook = new Book(1, "testBook", "testAuthor", 2010, false);

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfAuthorIsNotEquals() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        Book newBook = new Book(1, "testBook", "newBook", 2010, false);

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfPrintYearIsNotEquals() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        Book newBook = new Book(1, "testBook", "testAuthor", 2011, false);

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfIsReadIsNotEquals() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        Book newBook = new Book(1, "testBook", "testAuthor", 2010, true);

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsToNullObject() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        assertThat(book).isNotEqualTo(null);
    }

    @Test
    public void shouldBeNotEqualsIfSubClassHasNewContract() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        @Data
        @NoArgsConstructor
        @EqualsAndHashCode(callSuper = true)
        class BookWithDescription extends Book {

            private String description;

            private BookWithDescription(int id, String name, String author, String description, int printYear, boolean isRead) {
                super(id, name, author, printYear, isRead);
                this.description = description;
            }
        }

        BookWithDescription bookWithDescription = new BookWithDescription(1, "testBook", "testAuthor", "testDescription", 2010, false);

        assertThat(book).isNotEqualTo(bookWithDescription);
    }

    @Test
    public void shouldBeEqualsHashCode() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        Book copyBook = new Book(1, "testBook", "testAuthor", 2010, false);

        assertThat(book.hashCode()).isEqualTo(copyBook.hashCode());
    }

    @Test
    public void shouldBeEqualsHashCodeOfCopyObject() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        Book copyBook = book;

        assertThat(book.hashCode()).isEqualTo(copyBook.hashCode());
    }

    @Test
    public void shouldWorkHashCodeCorrectly() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        int actual = book.hashCode();

        int expected = 1;
        expected = expected * 59 + book.getId();
        Object name = book.getName();
        expected = expected * 59 + (name == null ? 43 : name.hashCode());
        Object author = book.getAuthor();
        expected = expected * 59 + (author == null ? 43 : author.hashCode());
        expected = expected * 59 + book.getPrintYear();
        expected = expected * 59 + (book.isRead() ? 79 : 97);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldBeNotEqualsHashCodeIfIdIsNotEquals() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        Book newBook = new Book(2, "testBook", "testAuthor", 2010, false);

        assertThat(book.hashCode()).isNotEqualTo(newBook.hashCode());
    }

    @Test
    public void shouldBeNotEqualsHashCodeIfNameNullIsNotEquals() {
        book = new Book(1, null, "testAuthor", 2010, false);

        Book newBook = new Book(1, "testBook", "testAuthor", 2010, false);

        assertThat(book.hashCode()).isNotEqualTo(newBook.hashCode());
    }

    @Test
    public void shouldBeNotEqualsHashCodeIfAuthorNullIsNotEquals() {
        book = new Book(1, "testBook", null, 2010, false);

        Book newBook = new Book(1, "testBook", "testAuthor", 2010, false);

        assertThat(book.hashCode()).isNotEqualTo(newBook.hashCode());
    }

    @Test
    public void shouldBeNotEqualsHashCodeIfPrintYearIsNotEquals() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        Book newBook = new Book(1, "testBook", "testAuthor", 2011, false);

        assertThat(book.hashCode()).isNotEqualTo(newBook.hashCode());
    }

    @Test
    public void shouldBeNotEqualsHashCodeIfIsReadIsNotEquals() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        Book newBook = new Book(1, "testBook", "testAuthor", 2010, true);

        assertThat(book.hashCode()).isNotEqualTo(newBook.hashCode());
    }

    @Test
    public void shouldBeNotEqualsHashCodeToNullObject() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        assertThat(book.hashCode()).isNotEqualTo(null);
    }

    @Test
    public void shouldWorkToStringCorrectly() {
        book = new Book(1, "testBook", "testAuthor", 2010, false);

        String expected = "Book(id=1, name=testBook, author=testAuthor, printYear=2010, isRead=false)";

        assertThat(book.toString()).isEqualTo(expected);
    }
}
