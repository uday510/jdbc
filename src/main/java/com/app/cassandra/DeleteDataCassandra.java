
package com.app.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.BoundStatement;

import java.util.UUID;

public class DeleteDataCassandra {
    private final CqlSession session;

    public DeleteDataCassandra(CqlSession session) {
        this.session = session;
    }

    public void deleteData(UUID id) {
        // Prepare the delete statement
        String deleteCQL = "DELETE FROM users WHERE id = ?";
        PreparedStatement preparedStatement = session.prepare(deleteCQL);
        BoundStatement boundStatement = preparedStatement.bind(id);

        // Execute the delete statement
        session.execute(boundStatement);
        System.out.println("Data deleted successfully from Cassandra.");
    }
}