
package com.app.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.BoundStatement;

import java.util.UUID;

public class UpdateDataCassandra {
    private final CqlSession session;

    public UpdateDataCassandra(CqlSession session) {
        this.session = session;
    }

    public void updateData(UUID id, int newAge) {
        // Prepare the update statement
        String updateCQL = "UPDATE users SET age = ? WHERE id = ?";
        PreparedStatement preparedStatement = session.prepare(updateCQL);
        BoundStatement boundStatement = preparedStatement.bind(newAge, id);

        // Execute the update statement
        session.execute(boundStatement);
        System.out.println("Data updated successfully in Cassandra.");
    }
}