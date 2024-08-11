package com.app.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class SelectDataMongoDB {
    private final MongoDatabase database;

    public SelectDataMongoDB(MongoDatabase database) {
        this.database = database;
    }

    public void selectData() {
        MongoCollection<Document> collection = database.getCollection("users");

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println(doc.toJson());
            }
        }
    }
}