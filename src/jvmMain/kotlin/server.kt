import com.benasher44.uuid.Uuid
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import models.Person
import org.litote.kmongo.*
import java.util.*


fun main() {
    val connectionString =
        ConnectionString("mongodb+srv://prowhiz:0co8nr3Z29Wv5XbQ@orsecluster.ops2n.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
    val settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .build()


    val client = KMongo.createClient(settings)
    val database = client.getDatabase("orsedatabase")
    val col = database.getCollection<Person>()


    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {

        routing {
            install(ContentNegotiation) {
                json()
            }
            install(CORS) {
                method(HttpMethod.Get)
                method(HttpMethod.Post)
                method(HttpMethod.Delete)
                anyHost()
            }
            install(Compression) {
                gzip()
            }

            get("/") {

            }
            post("/") {
                col.insertOne(Person("firstname","lastname", Uuid.randomUUID()))
                call.respond(HttpStatusCode.OK)
            }
            static("/") {
                resources("")
            }
        }
    }.start(wait = true)
}



