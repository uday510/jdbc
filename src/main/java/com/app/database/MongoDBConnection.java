package com.app.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;

public class MongoDBConnection implements DatabaseConnection<MongoDatabase> {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private static final Dotenv dotenv = Dotenv.load();

    private static final String MONGO_URL = dotenv.get("MONGO_URI");
    private static final String MONGO_DATABASE = dotenv.get("MONGO_DATABASE");

    private MongoDBConnection() {
        try {
            this.mongoClient = MongoClients.create(MONGO_URL);
            this.database = mongoClient.getDatabase(MONGO_DATABASE);
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to MongoDB");
        }
    }
    private static final class InstanceHolder {
        private static final MongoDBConnection INSTANCE = new MongoDBConnection();
    }
    public static MongoDBConnection getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public MongoDatabase getConnection() {
        return this.database;
    }

    @Override
    public void closeConnection() {
        if (this.mongoClient != null) {
            this.mongoClient.close();
        }
    }
}
