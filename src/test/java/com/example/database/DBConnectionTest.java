package com.example.database;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    void shouldCreateConnection() {
        assertDoesNotThrow(() -> {
            try (Connection connection = DBConnection.getConnection()) {
                assertNotNull(connection);
                assertFalse(connection.isClosed());
            }
        });
    }

    @Test
    void shouldCreateDBConnectionInstanceUsingReflection() throws Exception {
        Constructor<DBConnection> constructor = DBConnection.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        DBConnection instance = constructor.newInstance();
        assertNotNull(instance);
    }

}
