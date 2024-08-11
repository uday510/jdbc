package com.app.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectData {
    private final Connection connection;

    public SelectData(Connection connection) {
        this.connection = connection;
    }

    public void selectData() {
        String selectSQL = "SELECT * FROM users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String city = resultSet.getString("city");
                System.out.println("id: " + id + ", name: " + name + ", email: " + email + ", city: " + city);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error selecting data", e);
        }
    }
}
