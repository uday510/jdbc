package com.app.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class UpdateDataMongoDB {
    private final MongoDatabase database;

    public UpdateDataMongoDB(MongoDatabase database) {
        this.database = database;
    }

    public void updateData() {
        MongoCollection<Document> collection = database.getCollection("users");

        collection.updateOne(eq("name", "User1"), set("age", 26));
        System.out.println("Data updated in MongoDB successfully.");
    }
}