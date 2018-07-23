package com.qthegamep.bookmanager.exception;

/**
 * This exception should be thrown when any error occurred while loading database properties.
 */
public class LoadDBPropertiesException extends RuntimeException {

    public LoadDBPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }
}
