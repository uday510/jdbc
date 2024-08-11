package com.app.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class DeleteDataMongoDB {
    private final MongoDatabase database;

    public DeleteDataMongoDB(MongoDatabase database) {
        this.database = database;
    }

    public void deleteData() {
        MongoCollection<Document> collection = database.getCollection("users");

        collection.deleteOne(eq("name", "User1"));
        System.out.println("Data deleted from MongoDB successfully.");
    }
}