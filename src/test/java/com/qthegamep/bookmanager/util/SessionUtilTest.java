package com.qthegamep.bookmanager.util;

import com.qthegamep.bookmanager.exception.LoadDBPropertiesException;
import com.qthegamep.bookmanager.testhelper.rule.Rules;

import lombok.val;
import org.junit.*;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

public class SessionUtilTest {

    @ClassRule
    public static ExternalResource summaryRule = Rules.SUMMARY_RULE;

    @Rule
    public Stopwatch stopwatchRule = Rules.STOPWATCH_RULE;

    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        connection = SessionUtil.openConnection();
    }

    @After
    public void tearDown() throws SQLException {
        SessionUtil.closeConnection();
    }

    @Test
    public void shouldNotBeNullConnection() {
        assertThat(connection).isNotNull();
    }

    @Test
    public void shouldOpenConnection() throws SQLException {
        assertThat(connection.isClosed()).isFalse();
    }

    @Test
    public void shouldCloseConnection() throws SQLException {
        SessionUtil.closeConnection();

        assertThat(connection.isClosed()).isTrue();
    }

    @Test
    public void shouldNotCloseConnectionIfConnectionIsNull() throws NoSuchFieldException, IllegalAccessException {
        val connectionField = SessionUtil.class.getDeclaredField("connection");

        connectionField.setAccessible(true);
        connectionField.set(SessionUtil.class, null);

        assertThat(connectionField.get(SessionUtil.class)).isNull();

        connectionField.setAccessible(false);
    }

    @Test
    public void shouldLoadDBPropertiesIfUrlIsNull() throws NoSuchFieldException, IllegalAccessException, SQLException {
        val urlField = SessionUtil.class.getDeclaredField("URL");

        urlField.setAccessible(true);

        val oldUrlField = urlField.get(SessionUtil.class);

        urlField.set(SessionUtil.class, null);

        assertThat(urlField.get(SessionUtil.class)).isNull();

        connection = SessionUtil.openConnection();

        assertThat(connection.isClosed()).isFalse();
        assertThat(urlField.get(SessionUtil.class)).isEqualTo(oldUrlField);

        urlField.setAccessible(false);
    }

    @Test
    public void shouldLoadDBPropertiesIfUserIsNull() throws NoSuchFieldException, IllegalAccessException, SQLException {
        val userField = SessionUtil.class.getDeclaredField("USER");

        userField.setAccessible(true);

        val oldUserField = userField.get(SessionUtil.class);

        userField.set(SessionUtil.class, null);

        assertThat(userField.get(SessionUtil.class)).isNull();

        connection = SessionUtil.openConnection();

        assertThat(connection.isClosed()).isFalse();
        assertThat(userField.get(SessionUtil.class)).isEqualTo(oldUserField);

        userField.setAccessible(false);
    }

    @Test
    public void shouldLoadDBPropertiesIfPasswordIsNull() throws NoSuchFieldException, IllegalAccessException, SQLException {
        val passwordField = SessionUtil.class.getDeclaredField("PASSWORD");

        passwordField.setAccessible(true);

        val oldPasswordField = passwordField.get(SessionUtil.class);

        passwordField.set(SessionUtil.class, null);

        assertThat(passwordField.get(SessionUtil.class)).isNull();

        connection = SessionUtil.openConnection();

        assertThat(connection.isClosed()).isFalse();
        assertThat(passwordField.get(SessionUtil.class)).isEqualTo(oldPasswordField);

        passwordField.setAccessible(false);
    }

    @Test
    public void shouldThrowLoadDBPropertiesExceptionWhenDbPropertiesPathIsNotExist() throws NoSuchFieldException, IllegalAccessException {
        val userField = SessionUtil.class.getDeclaredField("USER");
        userField.setAccessible(true);

        val oldUserValue = userField.get(SessionUtil.class);

        userField.set(SessionUtil.class, null);

        val dbPropertiesPathField = SessionUtil.class.getDeclaredField("DB_PROPERTIES_PATH");
        dbPropertiesPathField.setAccessible(true);

        val modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(dbPropertiesPathField, dbPropertiesPathField.getModifiers() & ~Modifier.FINAL);

        val oldDbPropertiesPathValue = dbPropertiesPathField.get(SessionUtil.class);

        dbPropertiesPathField.set(SessionUtil.class, String.valueOf("test"));

        assertThatExceptionOfType(LoadDBPropertiesException.class).isThrownBy(
                () -> connection = SessionUtil.openConnection()
        ).withMessage("inStream parameter is null").withCauseInstanceOf(NullPointerException.class);

        userField.set(SessionUtil.class, oldUserValue);
        dbPropertiesPathField.set(SessionUtil.class, oldDbPropertiesPathValue);
        modifiers.setInt(dbPropertiesPathField, dbPropertiesPathField.getModifiers() | Modifier.FINAL);

        assertThat(userField.get(SessionUtil.class)).isEqualTo(oldUserValue);
        assertThat(dbPropertiesPathField.get(SessionUtil.class)).isEqualTo(oldDbPropertiesPathValue);
        assertThat(Modifier.isFinal(dbPropertiesPathField.getModifiers())).isTrue();

        modifiers.setAccessible(false);
        userField.setAccessible(false);
        dbPropertiesPathField.setAccessible(false);
    }

    @Test
    public void shouldThrowInvocationTargetExceptionWhenCreateObjectWithReflection() {
        assertThatExceptionOfType(InvocationTargetException.class).isThrownBy(() -> {
            val constructor = SessionUtil.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        }).withMessage(null).withCauseInstanceOf(UnsupportedOperationException.class);
    }
}
