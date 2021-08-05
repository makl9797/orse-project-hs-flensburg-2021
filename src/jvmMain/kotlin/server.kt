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
import models.Customer
import models.Person
import org.litote.kmongo.*
import services.DatabaseService


fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {

        val databaseService = DatabaseService()

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
                call.respond(HttpStatusCode.OK)
            }
            static("/") {
                resources("")
            }
        }
    }.start(wait = true)
}



