package com.qthegamep.bookmanager.util;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class is a utility helper class responsible for opening and closing a connection to the database.
 * We can modify the connection properties file. Any changes will cause a reload of the connection parameters
 * with the database from the settings file.
 */
@Slf4j
@UtilityClass
public class SessionUtil {

    private Connection connection;

    @Getter
    private String DB_PROPERTIES_PATH = "db/MySQL.properties";

    private String URL;
    private String USER;
    private String PASSWORD;

    private boolean isChangeDbPropertiesPath = false;

    /**
     * The method configures the path to the connection properties file.
     * After changing this field the connection parameters will be reloaded the next time you connect.
     *
     * @param dbPropertiesPath the path to the connection properties file.
     */
    public static void setDbPropertiesPath(String dbPropertiesPath) {
        DB_PROPERTIES_PATH = dbPropertiesPath;
        isChangeDbPropertiesPath = true;
        log.info("Database properties path was changed to [{}]", dbPropertiesPath);
    }

    /**
     * This method opens a connection to the database.
     * If the connection parameters are not configured or the path to the connection properties file has been changed,
     * then they will be loaded from the file.
     *
     * @return connection with database
     * @throws SQLException can be thrown when connection parameters are wrong.
     * @throws IOException  exception must be processed.
     */
    public Connection openConnection() throws SQLException, IOException {
        log.info("Preparing to open connection");

        if (URL == null || USER == null || PASSWORD == null || isChangeDbPropertiesPath) {
            loadDBProperties();
            log.info("Preparing to load database properties was done successful! Properties was load");
        }

        log.info("Parameters: URL={}, USER={}, PASSWORD={}", URL, USER, PASSWORD);
        connection = DriverManager.getConnection(URL, USER, PASSWORD);

        log.info("Preparing to open connection was done successful! Connection was opened");

        return connection;
    }

    /**
     * This method closes the connection to the database if there is a connection.
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

    private void loadDBProperties() throws IOException {
        log.info("Preparing to load database properties");

        val properties = new Properties();

        try (val inputStream = ClassLoader.getSystemResourceAsStream(DB_PROPERTIES_PATH)) {
            properties.load(inputStream);
            log.info("Properties file was load successful! Preparing to load information");

            URL = properties.getProperty("database.url");
            USER = properties.getProperty("database.user");
            PASSWORD = properties.getProperty("database.password");

            isChangeDbPropertiesPath = false;
        }
    }
}
