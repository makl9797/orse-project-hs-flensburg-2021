import dev.fritz2.identification.uniqueId
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import models.data.Address
import models.data.Customer
import routing.bookingRoutes
import routing.customerRoute
import routing.overviewRoutes
import routing.subjectRoutes
import services.DatabaseService

/**
 *This is the jvm Server.
 * Here we defined the Global settings for the Backend of this Application
 * Like Port, Host and Routing
 */

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
        /**
         * Here we installed the routes of this API
         */
        routing {
            subjectRoutes()
            overviewRoutes()
            bookingRoutes()
            createCustomer()
            getCustomerById()
            customerRoute()
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
        call.respondText(
            this::class.java.classLoader.getResource("index.html")!!.readText(),
            ContentType.Text.Html
        )
    }
    post("/") {
        call.respond(
            Customer(
                uniqueId(), Address(street = "street", city = "city", zip = 21344), "j??rgen", "hansen"
            )
        )
    }
}










