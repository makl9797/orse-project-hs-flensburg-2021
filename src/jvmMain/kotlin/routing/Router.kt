package routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import services.DatabaseService

open class Router {

    fun Route.getRoute() {
        get("/") {
            call.respond(HttpStatusCode.OK)
        }
    }

    fun Route.postRoute() {
        post("/") {
            call.respond(HttpStatusCode.OK)
        }
    }

}