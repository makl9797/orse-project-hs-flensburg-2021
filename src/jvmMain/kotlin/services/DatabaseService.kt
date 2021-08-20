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


open class DatabaseService {
    private var connectionString =
        ConnectionString("mongodb+srv://prowhiz:0co8nr3Z29Wv5XbQ@orsecluster.ops2n.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
    private var settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .build()
    private var client = KMongo.createClient(settings)
    private var database = client.getDatabase("orsedatabase")


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

