package com.example.database;

import java.io.InputStream;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseInitializer {
    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);

    private DatabaseInitializer() {}

    public static void initialize() {
        try (
                Connection conn = DBConnection.getConnection();
        ) {
            InputStream is = DatabaseInitializer.class.getResourceAsStream("/init.sql");
            if (is == null) {
                throw new IllegalStateException("init.sql not found");
            }
            String sql = new String(is.readAllBytes());

            for (String statement : sql.split(";")) {
                if (!statement.trim().isEmpty()) {
                    conn.createStatement().execute(statement);
                }
            }
        } catch (Exception e) {
            log.error("error: ", e);
        }
    }
}
