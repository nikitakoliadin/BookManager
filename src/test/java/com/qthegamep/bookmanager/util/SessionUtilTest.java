package com.qthegamep.bookmanager.util;

import com.qthegamep.bookmanager.testhelper.rule.Rules;

import lombok.val;
import org.junit.*;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;

import java.lang.reflect.InvocationTargetException;

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
    public void shouldBeTheSameConnection() throws SQLException {
        val copyConnection = SessionUtil.openConnection();

        assertThat(connection).isEqualTo(copyConnection);
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
    public void shouldSetAutoCommit() throws SQLException {
        SessionUtil.setAutoCommit(false);

        assertThat(connection.getAutoCommit()).isFalse();
    }

    @Test
    public void shouldAutoCommitBeTrueByDefault() throws SQLException {
        assertThat(connection.getAutoCommit()).isTrue();
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
