package com.app.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class SelectDataCassandra {
    private final CqlSession session;

    public SelectDataCassandra(CqlSession session) {
        this.session = session;
    }

    public void selectData() {
        String selectCQL = "SELECT * FROM users";

        ResultSet resultSet = session.execute(selectCQL);
        for (Row row : resultSet) {
            System.out.println("ID: " + row.getUuid("id"));
            System.out.println("Name: " + row.getString("name"));
            System.out.println("Email: " + row.getString("email"));
            System.out.println("Age: " + row.getInt("age"));
            System.out.println("City: " + row.getString("city"));
        }
    }
}