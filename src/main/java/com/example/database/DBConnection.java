package com.example.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    private static String getConfig(String key) {
        String value = System.getenv(key);

        if (value == null || value.isBlank()) {
            value = dotenv.get(key);
        }

        return value;
    }

    public static Connection getConnection() throws SQLException {

        String host = getConfig("DB_HOST");
        String port = getConfig("DB_PORT");
        String database = getConfig("DB_NAME");
        String user = getConfig("DB_USER");
        String password = getConfig("DB_PASSWORD");

        String url = String.format("jdbc:mariadb://%s:%s/%s", host, port, database);

        try {
            return DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {

            if ("host.docker.internal".equals(host)) {
                String localUrl = String.format("jdbc:mariadb://localhost:%s/%s", port, database);

                return DriverManager.getConnection(localUrl, user, password);
            }
            throw e;
        }
    }
}