import com.benasher44.uuid.Uuid
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import models.*
import services.DatabaseService
import java.sql.Timestamp

val databaseService = DatabaseService()

fun main() {

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
            static("/") {
                resources("")
            }
        }

        routing {
            getAppointmentById()
            createAppointment()
            createCustomer()
            getCustomerById()
            postRoot()
            getRoot()
        }
    }.start(wait = true)
}


fun Route.getAppointmentById() {
    get("/appointment/{id}") {
        call.respond(HttpStatusCode.OK)
    }
}

fun Route.createAppointment() {
    post("/appointment") {
        databaseService.getCollectionOfAppointment().insertOne(Appointment(Uuid.randomUUID(), System.currentTimeMillis(), call.parameters["info"].toString()))
        call.respond(HttpStatusCode.OK)
    }
}

fun Route.createCustomer() {
    post("/user") {
        call.respond(HttpStatusCode.OK)
    }
}

fun Route.getCustomerById() {
    post("/user/{id}") {
        call.respond(HttpStatusCode.OK)
    }
}

fun Route.getRoot() {
    get("/") {
        call.respond(HttpStatusCode.OK)
    }
}

fun Route.postRoot() {
    post("/") {
        call.respond(HttpStatusCode.OK)
    }
}









