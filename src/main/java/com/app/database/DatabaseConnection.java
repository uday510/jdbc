package com.app.database;

public interface DatabaseConnection<T> {
    T getConnection();
    void closeConnection();
}
