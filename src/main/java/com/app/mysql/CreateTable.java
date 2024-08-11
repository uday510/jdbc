package com.app.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    private final Connection connection;

    public CreateTable(Connection connection) {
        this.connection = connection;
    }

    public void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "age INT NOT NULL," +
                "email VARCHAR(100) NOT NULL," +
                "city VARCHAR(100) NOT NULL" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error creating table", e);
        }
    }
}
