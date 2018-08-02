package com.qthegamep.bookmanager.testhelper.util;

import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * This class is an utility helper class that is responsible for setting input and output to console.
 */
@UtilityClass
public class IOUtil {

    private final InputStream CONSOLE_INPUT_STREAM = System.in;

    private final PrintStream CONSOLE_PRINT_STREAM = System.out;

    /**
     * This method set input and output to the console. It is used before and after each test where the input
     * or output is substituted.
     */
    public void setInputOutputStreamToConsole() {
        System.setIn(CONSOLE_INPUT_STREAM);
        System.setOut(CONSOLE_PRINT_STREAM);
    }
}
