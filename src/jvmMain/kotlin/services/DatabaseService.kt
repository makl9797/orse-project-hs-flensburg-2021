package services

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoCollection
import models.data.Address
import models.data.Booking
import models.data.Customer
import models.data.Subject
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

// Create an open class DatabaseService to handle interaction with the Database
open class DatabaseService {
    // create the connection String, we used a cloud MongoDB
    private var connectionString =
        ConnectionString("mongodb+srv://prowhiz:0co8nr3Z29Wv5XbQ@orsecluster.ops2n.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
    // define the database access settings
    private var settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .build()
    // create a database client
    private var client = KMongo.createClient(settings)
    // get the database
    private var database = client.getDatabase("orsedatabase")

    // in the following there are a few functions that provide the Collections
    // (Collections are similar to Tables in non MongoDB Databases)
    fun getCollectionOfCustomer(): MongoCollection<Customer> {
        return database.getCollection<Customer>()
    }

    fun getCollectionOfBooking(): MongoCollection<Booking> {
        return database.getCollection<Booking>()
    }

    fun getCollectionOfAddress(): MongoCollection<Address> {
        return database.getCollection<Address>()
    }

    fun getCollectionOfSubject(): MongoCollection<Subject> {
        return database.getCollection<Subject>()
    }
}

