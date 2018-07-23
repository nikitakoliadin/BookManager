package com.qthegamep.bookmanager.exception;

import com.qthegamep.bookmanager.test.rule.Rules;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;

import static org.assertj.core.api.Assertions.*;

public class LoadDBPropertiesExceptionTest {

    @ClassRule
    public static ExternalResource summaryRule = Rules.SUMMARY_RULE;

    @Rule
    public Stopwatch stopwatchRule = Rules.STOPWATCH_RULE;

    @Test
    public void shouldThrowLoadDBPropertiesExceptionCorrectly() {
        assertThatExceptionOfType(LoadDBPropertiesException.class).isThrownBy(() -> {
            throw new LoadDBPropertiesException("Failed to load database properties", new NullPointerException("USER is null"));
        }).withMessage("Failed to load database properties").withCauseInstanceOf(NullPointerException.class);
    }
}
