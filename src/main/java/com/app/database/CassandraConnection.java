
package com.app.database;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.config.ProgrammaticDriverConfigLoaderBuilder;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.InetSocketAddress;


/**
 * Mon Aug 12 02:13:49 IST 2024
 */

public class CassandraConnection implements DatabaseConnection<CqlSession> {
    private CqlSession session;
    private static final Dotenv dotenv = Dotenv.load();

    private static final String CASSANDRA_CONTACT_POINT = dotenv.get("CASSANDRA_CONTACT_POINT");
    private static final int CASSANDRA_PORT = Integer.parseInt(dotenv.get("CASSANDRA_PORT"));
    private static final String CASSANDRA_KEYSPACE = dotenv.get("CASSANDRA_KEYSPACE");
    private static final String DATACENTER = dotenv.get("CASSANDRA_DATACENTER");

    private CassandraConnection() {
        try {
            ProgrammaticDriverConfigLoaderBuilder configLoaderBuilder = DriverConfigLoader.programmaticBuilder()
                    .withString(DefaultDriverOption.LOAD_BALANCING_LOCAL_DATACENTER, DATACENTER);

            this.session = CqlSession.builder()
                    .addContactPoint(new InetSocketAddress(CASSANDRA_CONTACT_POINT, CASSANDRA_PORT))
                    .withConfigLoader(configLoaderBuilder.build())
                    .withLocalDatacenter(DATACENTER)
                    .withKeyspace(CASSANDRA_KEYSPACE)
                    .build();

            System.out.println("Connected to Cassandra cluster: " + this.session.getMetadata().getClusterName());
        } catch (Exception e) {
            System.out.println("Error connecting to Cassandra: " + e.getMessage());
            this.session = null;
        }
    }

    private static final class InstanceHolder {
        private static final CassandraConnection INSTANCE = new CassandraConnection();
    }

    public static CassandraConnection getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public CqlSession getConnection() {
        return this.session;
    }

    @Override
    public void closeConnection() {
        if (this.session != null) {
            this.session.close();
            System.out.println("Cassandra connection closed");
        }
    }
}