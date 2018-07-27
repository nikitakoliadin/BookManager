package com.qthegamep.bookmanager;

import com.qthegamep.bookmanager.testhelper.rule.Rules;

import lombok.val;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.*;

public class ApplicationTest {

    @ClassRule
    public static ExternalResource summaryRule = Rules.SUMMARY_RULE;

    @Rule
    public Stopwatch stopwatchRule = Rules.STOPWATCH_RULE;
    @Rule
    public ExternalResource inputOutputSetup = Rules.INPUT_OUTPUT_SETUP_RULE;

    @Test
    public void shouldWorkApplication() {
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(byteArrayOutputStream));

        Application.main(null);

        val actual = byteArrayOutputStream.toString();
        val expected = "Realization is empty!" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldThrowInvocationTargetExceptionWhenCreateObjectWithReflection() {
        assertThatExceptionOfType(InvocationTargetException.class).isThrownBy(() -> {
            val constructor = Application.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        }).withMessage(null).withCauseInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenSystemOutIsNull() {
        System.setOut(null);

        assertThatNullPointerException().isThrownBy(
                () -> Application.main(null)
        ).withMessage(null);
    }
}
