package com.app;

import com.datastax.oss.driver.api.core.CqlSession;

import static java.lang.Class.forName;

public class Cassandra {
    public static void main(String[] args) {
        try {

            // Load the Cassandra driver
            forName("com.datastax.oss.driver.api.core.CqlSession");

            // Connect to the database
            var session = CqlSession.builder().build();

            // Create a keyspace
            session.execute("CREATE KEYSPACE IF NOT EXISTS jdbc WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1}");

            // Create a table
            session.execute("CREATE TABLE IF NOT EXISTS jdbc.users (id INT PRIMARY KEY, name TEXT, email TEXT)");

            // Insert a record
            session.execute("INSERT INTO jdbc.users (id, name, email) VALUES (1, 'John Doe', 'john@doe.com')");

            // Select all records

            var resultSet = session.execute("SELECT * FROM jdbc.users");

            // Iterate over the result set
            resultSet.forEach(row -> System.out.println(row.getInt("id") + " " + row.getString("name") + " " + row.getString("email")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.exit(0);
        }
    }
}
