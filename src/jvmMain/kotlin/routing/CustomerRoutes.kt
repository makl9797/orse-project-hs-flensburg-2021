package routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.Identity.decode
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Customer

import java.time.*


fun Route.customerRoute() {
    post("/customer/create") {
        try {
            val customer = Customer("dfgdfg","f","l")
            call.respondText(Json.encodeToString(customer))

        } catch (e: Exception) {
            call.respondText(e.toString())
            call.respond(HttpStatusCode.BadRequest)
        }
        call.respond(HttpStatusCode.OK)
    }
}


