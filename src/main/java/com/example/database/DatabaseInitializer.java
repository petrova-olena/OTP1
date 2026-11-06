package com.example.database;

import java.io.InputStream;
import java.sql.Connection;

public class DatabaseInitializer {

    public static void initialize() {
        try (
                Connection conn = DBConnection.getConnection();
                InputStream is =
                        DatabaseInitializer.class.getResourceAsStream("/init.sql")
        ) {
            String sql = new String(is.readAllBytes());

            for (String statement : sql.split(";")) {
                if (!statement.trim().isEmpty()) {
                    conn.createStatement().execute(statement);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
