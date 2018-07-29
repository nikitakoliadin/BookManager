package com.qthegamep.bookmanager.entity;

import com.qthegamep.bookmanager.testhelper.rule.Rules;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;
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
    private Book newBook;

    @Before
    public void setUp() {
        book = new Book();

        book.setId(1);
        book.setName("test book");
        book.setAuthor("test author");
        book.setPrintYear(2000);
        book.setRead(false);

        newBook = new Book();

        newBook.setId(1);
        newBook.setName("test book");
        newBook.setAuthor("test author");
        newBook.setPrintYear(2000);
        newBook.setRead(false);
    }

    @Test
    public void shouldCreateObjectWithNoArgsConstructor() {
        assertThat(book).isNotNull();
    }

    @Test
    public void shouldGetAndSetId() {
        val id = 2;

        book.setId(id);

        assertThat(book.getId()).isEqualTo(id);
    }

    @Test
    public void shouldGetAndSetName() {
        val name = "testBook";

        book.setName(name);

        assertThat(book.getName()).isEqualTo(name);
    }

    @Test
    public void shouldGetAndSetAuthor() {
        val author = "testAuthor";

        book.setAuthor(author);

        assertThat(book.getAuthor()).isEqualTo(author);
    }

    @Test
    public void shouldGetAndSetPrintYear() {
        val printYear = 2010;

        book.setPrintYear(printYear);

        assertThat(book.getPrintYear()).isEqualTo(printYear);
    }

    @Test
    public void shouldGetAndSetIsRead() {
        val isRead = true;

        book.setRead(isRead);

        assertThat(book.isRead()).isEqualTo(isRead);
    }

    @Test
    public void shouldBeEquals() {
        assertThat(book).isEqualTo(newBook);
    }

    @Test
    public void shouldBeEqualsWithNullName() {
        book.setName(null);
        newBook.setName(null);

        assertThat(book).isEqualTo(newBook);
    }

    @Test
    public void shouldBeEqualsWithNullAuthor() {
        book.setAuthor(null);
        newBook.setAuthor(null);

        assertThat(book).isEqualTo(newBook);
    }

    @Test
    public void shouldBeEqualsOfCopyObject() {
        newBook = book;

        assertThat(book).isEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfIdIsNotEquals() {
        newBook.setId(2);

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfNameOfFirstObjectIsNull() {
        book.setName(null);

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfNameIsNotEquals() {
        newBook.setName("newBook");

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfAuthorOfFirstObjectIsNull() {
        book.setAuthor(null);

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfAuthorIsNotEquals() {
        newBook.setAuthor("newAuthor");

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfPrintYearIsNotEquals() {
        newBook.setPrintYear(2011);

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfIsReadIsNotEquals() {
        newBook.setRead(true);

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsToNullObject() {
        newBook = null;

        assertThat(book).isNotEqualTo(newBook);
    }

    @Test
    public void shouldBeNotEqualsIfSubClassHasNewContract() {

        @Data
        @EqualsAndHashCode(callSuper = true)
        class BookWithDescription extends Book {

            private String description;
        }

        val bookWithDescription = new BookWithDescription();

        bookWithDescription.setId(1);
        bookWithDescription.setName("testBook");
        bookWithDescription.setAuthor("testAuthor");
        bookWithDescription.setDescription("testDescription");
        bookWithDescription.setPrintYear(2010);
        bookWithDescription.setRead(false);

        assertThat(book).isNotEqualTo(bookWithDescription);
    }

    @Test
    public void shouldBeEqualsHashCode() {
        assertThat(book.hashCode()).isEqualTo(newBook.hashCode());
    }

    @Test
    public void shouldBeEqualsHashCodeOfCopyObject() {
        newBook = book;

        assertThat(book.hashCode()).isEqualTo(newBook.hashCode());
    }

    @Test
    public void shouldWorkHashCodeCorrectly() {
        val actual = book.hashCode();

        int expected = 1;
        expected = expected * 59 + book.getId();
        val name = book.getName();
        expected = expected * 59 + (name == null ? 43 : name.hashCode());
        val author = book.getAuthor();
        expected = expected * 59 + (author == null ? 43 : author.hashCode());
        expected = expected * 59 + book.getPrintYear();
        expected = expected * 59 + (book.isRead() ? 79 : 97);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldBeNotEqualsHashCodeIfIdIsNotEquals() {
        newBook.setId(2);

        assertThat(book.hashCode()).isNotEqualTo(newBook.hashCode());
    }

    @Test
    public void shouldBeNotEqualsHashCodeIfNameOfFirstObjectIsNull() {
        book.setName(null);

        assertThat(book.hashCode()).isNotEqualTo(newBook.hashCode());
    }

    @Test
    public void shouldBeNotEqualsHashCodeIfAuthorOfFirstObjectIsNull() {
        book.setAuthor(null);

        assertThat(book.hashCode()).isNotEqualTo(newBook.hashCode());
    }

    @Test
    public void shouldBeNotEqualsHashCodeIfPrintYearIsNotEquals() {
        newBook.setPrintYear(2011);

        assertThat(book.hashCode()).isNotEqualTo(newBook.hashCode());
    }

    @Test
    public void shouldBeNotEqualsHashCodeIfIsReadIsNotEquals() {
        newBook.setRead(true);

        assertThat(book.hashCode()).isNotEqualTo(newBook.hashCode());
    }

    @Test
    public void shouldBeNotEqualsHashCodeToNullObject() {
        newBook = null;

        assertThat(book.hashCode()).isNotEqualTo(newBook);
    }

    @Test
    public void shouldWorkToStringCorrectly() {
        val expected = "Book(id=1, name=test book, author=test author, printYear=2000, isRead=false)";

        assertThat(book.toString()).isEqualTo(expected);
    }
}
