package com.qthegamep.bookmanager.util;

import com.qthegamep.bookmanager.exception.LoadDBPropertiesException;
import com.qthegamep.bookmanager.testhelper.rule.Rules;

import lombok.val;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class PropertiesUtilTest {

    @ClassRule
    public static ExternalResource summaryRule = Rules.SUMMARY_RULE;

    @Rule
    public Stopwatch stopwatchRule = Rules.STOPWATCH_RULE;

    private static String url;
    private static String user;
    private static String password;

    @BeforeClass
    public static void loadRealProperties() throws IOException {
        val properties = new Properties();

        try (val inputStream = ClassLoader.getSystemResourceAsStream("DB.properties")) {
            properties.load(inputStream);

            url = properties.getProperty("database.url");
            user = properties.getProperty("database.user");
            password = properties.getProperty("database.password");
        }
    }

    @Test
    public void shouldGetUrlCorrectly() {
        assertThat(PropertiesUtil.getUrl()).isNotNull().isEqualTo(url);
    }

    @Test
    public void shouldGetUserCorrectly() {
        assertThat(PropertiesUtil.getUser()).isNotNull().isEqualTo(user);
    }

    @Test
    public void shouldGetPasswordCorrectly() {
        assertThat(PropertiesUtil.getPassword()).isNotNull().isEqualTo(password);
    }

    @Test
    public void shouldThrowLoadDBPropertiesExceptionWhenDbPropertiesPathIsNotExist() throws NoSuchFieldException, IllegalAccessException {
        val dbPropertiesPathField = PropertiesUtil.class.getDeclaredField("DB_PROPERTIES_PATH");
        dbPropertiesPathField.setAccessible(true);

        val modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(dbPropertiesPathField, dbPropertiesPathField.getModifiers() & ~Modifier.FINAL);

        val oldDbPropertiesPathValue = dbPropertiesPathField.get(PropertiesUtil.class);

        dbPropertiesPathField.set(PropertiesUtil.class, String.valueOf("test"));

        assertThatExceptionOfType(LoadDBPropertiesException.class).isThrownBy(PropertiesUtil::getUrl)
                .withMessage("inStream parameter is null")
                .withCauseInstanceOf(NullPointerException.class);

        dbPropertiesPathField.set(PropertiesUtil.class, oldDbPropertiesPathValue);
        modifiers.setInt(dbPropertiesPathField, dbPropertiesPathField.getModifiers() | Modifier.FINAL);

        assertThat(dbPropertiesPathField.get(PropertiesUtil.class)).isEqualTo(oldDbPropertiesPathValue);
        assertThat(Modifier.isFinal(dbPropertiesPathField.getModifiers())).isTrue();

        modifiers.setAccessible(false);
        dbPropertiesPathField.setAccessible(false);
    }

    @Test
    public void shouldThrowInvocationTargetExceptionWhenCreateObjectWithReflection() {
        assertThatExceptionOfType(InvocationTargetException.class).isThrownBy(() -> {
            val constructor = PropertiesUtil.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        }).withMessage(null).withCauseInstanceOf(UnsupportedOperationException.class);
    }
}
