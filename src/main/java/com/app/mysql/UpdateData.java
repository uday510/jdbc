package com.app.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
    private final Connection connection;

    public UpdateData(Connection connection) {
        this.connection = connection;
    }

    public void updateData() {
        String updateSQL = "UPDATE users SET age = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setInt(1, 35);
            preparedStatement.setInt(2, 1);

            preparedStatement.executeUpdate();
            System.out.println("Data updated successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating data", e);
        }
    }
}
