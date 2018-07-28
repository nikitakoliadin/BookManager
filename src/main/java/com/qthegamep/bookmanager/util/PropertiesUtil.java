package com.qthegamep.bookmanager.util;

import com.qthegamep.bookmanager.exception.LoadDBPropertiesException;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Properties;

/**
 * This class is aa utility helper class that is responsible for loading properties from the database properties file.
 */
@Slf4j
@UtilityClass
public class PropertiesUtil {

    private final String DB_PROPERTIES_PATH = String.valueOf("DB.properties");

    /**
     * This method loads the URL property and returns it as a string.
     *
     * @return string that contains URL connection parameter.
     */
    public String getUrl() {
        return getProperty("url");
    }

    /**
     * This method loads the USER property and returns it as a string.
     *
     * @return string that contains USER connection parameter.
     */
    public String getUser() {
        return getProperty("user");
    }

    /**
     * This method loads the PASSWORD property and returns it as a string.
     *
     * @return string that contains PASSWORD connection parameter.
     */
    public String getPassword() {
        return getProperty("password");
    }

    private String getProperty(String propertyName) {
        log.info("Preparing to load database {}", propertyName.toUpperCase());

        val properties = new Properties();

        log.info("Preparing to load properties file. Database properties path: [{}]", DB_PROPERTIES_PATH);
        try (val inputStream = ClassLoader.getSystemResourceAsStream(DB_PROPERTIES_PATH)) {
            properties.load(inputStream);
            log.info("Preparing to load properties file was done successful! Preparing to load {}", propertyName.toUpperCase());

            val property = properties.getProperty("database." + propertyName);
            log.info("Preparing to load database {} was done successful! {}: [{}]",
                    propertyName.toUpperCase(),
                    propertyName.toUpperCase(),
                    property
            );

            return property;
        } catch (Exception e) {
            log.error("Failed to load database {}, message: [{}]",
                    propertyName.toUpperCase(),
                    e.getMessage(),
                    e
            );
            throw new LoadDBPropertiesException(e.getMessage(), e);
        }
    }
}
