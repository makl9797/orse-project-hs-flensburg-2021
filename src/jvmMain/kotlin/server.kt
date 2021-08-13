import com.benasher44.uuid.Uuid
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.*
import routing.*
import services.DatabaseService


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
            subjectRoutes()
            overviewRoutes()
            appointmentRoutes()
            bookingRoutes()
            createCustomer()
            getCustomerById()
            customerRoute()
            postRoot()
            getRoot()
        }
    }.start(wait = true)
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
        try {
            val booking = Booking(
                5.0,
                Customer(Uuid.randomUUID().toString(), Address("Bahnhofsweg", "Flensburg", 24954), "Hans", "Peter"),
                "2021-08-23",
                "2021-09-01",
                Subject("Subjectname", "Subscription", Uuid.randomUUID().toString())
            )
            call.respondText(Json.encodeToString(booking))
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respondText(e.toString())
        }

    }
}









