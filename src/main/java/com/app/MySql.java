package com.app;
import java.sql.*;
import java.util.logging.Logger;

public class MySql {
    public static void main(String[] args) {
        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jdbc", "root", "root1234");

            // Create a statement
            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), email VARCHAR(50))");

            // Insert a record
            statement.executeUpdate("INSERT INTO users (name, email) VALUES ('John Doe', 'john@doe.com')");

            // Select all records
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            // Iterate over the result set
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") + " " + resultSet.getString("email"));
            }

        } catch (Exception e) {
            Logger.getAnonymousLogger().info(e.getMessage());
        }
    }
}
