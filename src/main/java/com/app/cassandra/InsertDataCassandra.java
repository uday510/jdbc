package com.app.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.uuid.Uuids;

public class InsertDataCassandra {
    private final CqlSession session;

    public InsertDataCassandra(CqlSession session) {
        this.session = session;
    }

    public void insertData() {
        String insertCQL = "INSERT INTO users (id, name, email, age, city) VALUES (" +
                Uuids.timeBased() + ", 'User1', 'user1@email.com', 25, 'City1')";

        session.execute(insertCQL);
        System.out.println("Data inserted successfully into Cassandra.");
    }
}