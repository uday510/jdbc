import com.app.cassandra.*;
import com.app.database.CassandraConnection;
import com.app.database.MySQLConnection;
import com.app.database.MongoDBConnection;
import com.app.mongodb.DeleteDataMongoDB;
import com.app.mongodb.InsertDataMongoDB;
import com.app.mongodb.SelectDataMongoDB;
import com.app.mongodb.UpdateDataMongoDB;
import com.app.mysql.*;

import java.util.UUID;

public class App {
    public static void main(String[] args) {
        // Test MySQL CRUD Operations
//        System.out.println("Testing MySQL Operations...");
//        var mysqlConnection = MySQLConnection.getInstance();
        try {
//            var mysqlConn = mysqlConnection.getConnection();
//            CreateTable createTable = new CreateTable(mysqlConn);
//            createTable.createTable();

//            InsertData insertData = new InsertData(mysqlConn);
//            insertData.insertData();

//            SelectData selectData = new SelectData(mysqlConn);
//            selectData.selectData();

//            UpdateData updateData = new UpdateData(mysqlConn);
//            updateData.updateData();

//            DeleteData deleteData = new DeleteData(mysqlConn);
//            deleteData.deleteData();
        } finally {
//            mysqlConnection.closeConnection();
        }

        // Test MongoDB CRUD Operations
//        System.out.println("Testing MongoDB Operations...");
//        var mongoConnection = MongoDBConnection.getInstance();
//        try {
//            var mongoDb = mongoConnection.getConnection();
//            InsertDataMongoDB insertDataMongo = new InsertDataMongoDB(mongoDb);
//            insertDataMongo.insertData();
//
//            SelectDataMongoDB selectDataMongo = new SelectDataMongoDB(mongoDb);
//            selectDataMongo.selectData();
//
//            UpdateDataMongoDB updateDataMongo = new UpdateDataMongoDB(mongoDb);
//            updateDataMongo.updateData();

//            DeleteDataMongoDB deleteDataMongo = new DeleteDataMongoDB(mongoDb);
//            deleteDataMongo.deleteData();
//        } finally {
//            mongoConnection.closeConnection();
//        }

        // Test Cassandra CRUD Operations
        System.out.println("Testing Cassandra Operations...");
        var cassandraConnection = CassandraConnection.getInstance();
        try {
            var cassandraSession = cassandraConnection.getConnection();
            CreateTableCassandra createTableCassandra = new CreateTableCassandra(cassandraSession);
            createTableCassandra.createTable();

            InsertDataCassandra insertDataCassandra = new InsertDataCassandra(cassandraSession);
            insertDataCassandra.insertData();

            SelectDataCassandra selectDataCassandra = new SelectDataCassandra(cassandraSession);
            selectDataCassandra.selectData();

            UpdateDataCassandra updateDataCassandra = new UpdateDataCassandra(cassandraSession);
            UUID id = UUID.fromString("90322f90-582f-11ef-9d7a-5195a8fd48ee");
            updateDataCassandra.updateData(id, 30);

            DeleteDataCassandra deleteDataCassandra = new DeleteDataCassandra(cassandraSession);
            deleteDataCassandra.deleteData(id);
        } finally {
            cassandraConnection.closeConnection();
        }
    }
}