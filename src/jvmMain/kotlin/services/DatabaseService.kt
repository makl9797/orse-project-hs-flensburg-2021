package services

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoCollection
import models.Customer
import models.Person
import org.litote.kmongo.*


open class DatabaseService {
    private var connectionString =
        ConnectionString("mongodb+srv://prowhiz:0co8nr3Z29Wv5XbQ@orsecluster.ops2n.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
    private var settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .build()
    private var client = KMongo.createClient(settings)
    private var database = client.getDatabase("orsedatabase")

    fun getCollectionOfPerson(): MongoCollection<Person> {
        return database.getCollection<Person>()
    }

    fun getCollectionOfCustomer(): MongoCollection<Customer> {
        return database.getCollection<Customer>()
    }
}

