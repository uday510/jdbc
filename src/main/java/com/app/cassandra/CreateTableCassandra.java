package com.app.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;

public class CreateTableCassandra {
    private final CqlSession session;

    public CreateTableCassandra(CqlSession session) {
        this.session = session;
    }

    public void createTable() {
        String createTableCQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id UUID PRIMARY KEY, " +
                "name text, " +
                "email text, " +
                "age int, " +
                "city text" +
                ");";

        session.execute(createTableCQL);
        System.out.println("Table created successfully in Cassandra.");
    }
}