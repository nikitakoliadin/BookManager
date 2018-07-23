package com.qthegamep.bookmanager.util;

import com.qthegamep.bookmanager.exception.LoadDBPropertiesException;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class is a utility helper class responsible for opening and closing a connection to the database.
 */
@Slf4j
@UtilityClass
public class SessionUtil {

    private final String DB_PROPERTIES_PATH = String.valueOf("db/MySQL.properties");

    private Connection connection;

    private String URL;
    private String USER;
    private String PASSWORD;

    /**
     * This method opens a connection to the database.
     * If the connection parameters are not configured it will be loaded from the properties file.
     *
     * @return connection with database
     * @throws SQLException              can be thrown when connection parameters are wrong.
     * @throws LoadDBPropertiesException can be thrown when any error occurred while loading database properties.
     */
    public Connection openConnection() throws SQLException, LoadDBPropertiesException {
        log.info("Preparing to open connection");

        if (URL == null || USER == null || PASSWORD == null) {
            loadDBProperties();
        }

        log.info("Database connection parameters: URL={}, USER={}, PASSWORD={}", URL, USER, PASSWORD);
        connection = DriverManager.getConnection(URL, USER, PASSWORD);

        log.info("Preparing to open connection was done successful! Connection was opened");

        return connection;
    }

    /**
     * This method closes the connection to the database if there is open connection.
     *
     * @throws SQLException exception must be processed.
     */
    public void closeConnection() throws SQLException {
        log.info("Preparing to close connection");

        if (connection != null && !connection.isClosed()) {
            connection.close();
            log.info("Preparing to close connection was done successful! Connection was closed");
        } else {
            log.info("Preparing to close connection was done successful! Connection was not closed because connection was not opened");
        }
    }

    private void loadDBProperties() {
        log.info("Preparing to load database properties");

        val properties = new Properties();

        log.info("Database properties path: [{}]", DB_PROPERTIES_PATH);
        try (val inputStream = ClassLoader.getSystemResourceAsStream(DB_PROPERTIES_PATH)) {
            properties.load(inputStream);
            log.info("Properties file was load successful! Preparing to load information");

            URL = properties.getProperty("database.url");
            USER = properties.getProperty("database.user");
            PASSWORD = properties.getProperty("database.password");
        } catch (Exception e) {
            log.error("Failed to load database properties, message: [{}]",
                    e.getMessage(),
                    e
            );
            throw new LoadDBPropertiesException(e.getMessage(), e);
        }

        log.info("Preparing to load database properties was done successful! Properties was load");
    }
}
