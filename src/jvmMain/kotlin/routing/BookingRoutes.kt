package routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.bookingRoutes() {
    post("/booking") {
        call.respond(HttpStatusCode.OK)
    }
    get("/booking") {
        call.respond(HttpStatusCode.OK)
    }
}