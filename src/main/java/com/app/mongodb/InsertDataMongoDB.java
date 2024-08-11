package com.app.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class InsertDataMongoDB {
    private final MongoDatabase database;

    public InsertDataMongoDB(MongoDatabase database) {
        this.database = database;
    }

    public void insertData() {
        MongoCollection<Document> collection = database.getCollection("users");

        Document user = new Document("name", "User1")
                .append("email", "user1@email.com")
                .append("age", 25)
                .append("city", "City1");

        collection.insertOne(user);
        System.out.println("Data inserted into MongoDB successfully.");
    }
}