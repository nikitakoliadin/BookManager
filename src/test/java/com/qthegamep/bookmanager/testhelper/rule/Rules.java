package com.qthegamep.bookmanager.testhelper.rule;

import com.qthegamep.bookmanager.testhelper.util.IOUtil;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@UtilityClass
public class Rules {

    /**
     * The constant is used as a rule for calculating of the time spent by a test.
     * It is used as @Rule JUnit annotation.
     */
    public final Stopwatch STOPWATCH_RULE = new Stopwatch() {

        @Override
        protected void finished(long nanos, @NotNull Description description) {
            val result = String.format("%-144s %7d",
                    description.getDisplayName(),
                    TimeUnit.NANOSECONDS.toMillis(nanos)
            );

            className = description.getClassName();
            results.append(result).append(System.lineSeparator());
            log.info(result);
        }
    };

    /**
     * The constant is used as a rule for output results of the class tests.
     * It is used as @ClassRule JUnit annotation.
     */
    public final ExternalResource SUMMARY_RULE = new ExternalResource() {

        @Override
        protected void before() {
            results.setLength(0);
        }

        @Override
        protected void after() {
            val infoLine = String.format("Test of %-131s %12s",
                    className,
                    "Duration, ms"
            );
            log.info("--------------------------------------------------------------------------------------------------------------------------------------------------------"
                    + System.lineSeparator()
                    + infoLine
                    + System.lineSeparator()
                    + "--------------------------------------------------------------------------------------------------------------------------------------------------------"
                    + System.lineSeparator()
                    + results
                    + "--------------------------------------------------------------------------------------------------------------------------------------------------------"
                    + System.lineSeparator()
            );
        }
    };

    /**
     * This constant is used as a rule for configure input and output on the console.
     * It is used as @Rule JUnit annotation.
     */
    public final ExternalResource INPUT_OUTPUT_SETUP_RULE = new ExternalResource() {

        @Override
        protected void before() {
            IOUtil.setInputOutputStreamToConsole();
        }

        @Override
        protected void after() {
            IOUtil.setInputOutputStreamToConsole();
        }
    };

    private final Logger log = LoggerFactory.getLogger("TEST_RESULT_LOGGER");

    private final StringBuilder results = new StringBuilder();

    private String className;
}
