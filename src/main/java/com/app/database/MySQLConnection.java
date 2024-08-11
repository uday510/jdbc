package com.app.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.SQLException;


public class MySQLConnection implements DatabaseConnection<Connection> {
    private Connection connection;
    private static final Dotenv dotenv = Dotenv.load();

    private static final String JDBC_URL = dotenv.get("JDBC_URL");
    private static final String JDBC_USER = dotenv.get("JDBC_USER");
    private static final String JDBC_PASSWORD = dotenv.get("JDBC_PASSWORD");
    private static final String JDBC_DRIVER = dotenv.get("JDBC_DRIVER");

    private MySQLConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            this.connection = java.sql.DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error connecting to the database");
        }
    }

    private static final class InstanceHolder {
        private static final MySQLConnection INSTANCE = new MySQLConnection();
    }

    public static MySQLConnection getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error closing the database connection");
        }
    }
}
