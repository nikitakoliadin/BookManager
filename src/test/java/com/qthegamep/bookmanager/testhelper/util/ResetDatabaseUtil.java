package com.qthegamep.bookmanager.testhelper.util;

import com.qthegamep.bookmanager.util.SessionUtil;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * This class is an utility helper class that is responsible for resetting database to empty state.
 */
@UtilityClass
public class ResetDatabaseUtil {

    private final String RESET_DATABASE_SQL_QUERY = getResetDatabaseSqlQuery();

    /**
     * This method reset database to empty state using system resource that can switch by maven profile.
     * The sql query will be loaded only once.
     */
    public void resetDatabase() {
        try {
            val connection = SessionUtil.openConnection();

            try (val statement = connection.createStatement()) {
                statement.executeUpdate(RESET_DATABASE_SQL_QUERY);
            } finally {
                SessionUtil.closeConnection();
            }
        } catch (SQLException ignore) {
        }
    }

    private String getResetDatabaseSqlQuery() {
        String resetDatabaseSqlQuery = null;

        try {
            val bytes = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("initDB.sql").toURI()));

            resetDatabaseSqlQuery = new String(bytes);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return resetDatabaseSqlQuery;
    }
}
