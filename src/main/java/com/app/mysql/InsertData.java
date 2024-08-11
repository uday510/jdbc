package com.app.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    private final Connection connection;

    public InsertData(Connection connection) {
        this.connection = connection;
    }

    public void insertData() {
        String insertSQL = "INSERT INTO users (name, email, age, city) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, "John Doe");
            preparedStatement.setString(2, "john@email.com");
            preparedStatement.setInt(3, 30);
            preparedStatement.setString(4, "New York");

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting data", e);
        }

    }
}
