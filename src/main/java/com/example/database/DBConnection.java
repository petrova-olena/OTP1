package com.example.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static Connection getConnection()
            throws SQLException {

        String host = dotenv.get("DB_HOST");
        String port = dotenv.get("DB_PORT");
        String database = dotenv.get("DB_NAME");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        String url = String.format("jdbc:mariadb://%s:%s/%s", host, port, database);

        return DriverManager.getConnection(url, user, password);
    }
}