package com.qthegamep.bookmanager.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is an utility helper class responsible for opening and closing connection to the database.
 */
@Slf4j
@UtilityClass
public class SessionUtil {

    private final String URL = PropertiesUtil.getUrl();
    private final String USER = PropertiesUtil.getUser();
    private final String PASSWORD = PropertiesUtil.getPassword();

    private Connection connection;

    /**
     * This method opens the connection to the database.
     *
     * @return connection to the database.
     * @throws SQLException that must be processed.
     */
    public Connection openConnection() throws SQLException {
        log.info("Preparing to open connection");

        if (connection != null && !connection.isClosed()) {
            log.info("Preparing to open connection was done successful! Connection was not opened because it was already opened");
            return connection;
        }

        log.info("Database connection parameters: URL={}, USER={}, PASSWORD={}", URL, USER, PASSWORD);
        connection = DriverManager.getConnection(URL, USER, PASSWORD);

        log.info("Preparing to open connection was done successful! Connection was opened");

        return connection;
    }

    /**
     * This method closes the connection to the database if there is open connection or connection isn't closed.
     *
     * @throws SQLException that must be processed.
     */
    public void closeConnection() throws SQLException {
        log.info("Preparing to close connection");

        if (connection != null && !connection.isClosed()) {
            connection.close();
            log.info("Preparing to close connection was done successful! Connection was closed");
        } else {
            log.info("Preparing to close connection was done successful! Connection was not closed because connection was not opened or was closed already");
        }
    }
}
