package routing

import dev.Devroom
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import java.time.*


fun Route.customerRoute() {
    post("/customer/create") {
        try {
            //  val customer = Customer("CUSTOMER_ID")
            //val col = databaseService.getCollectionOfCustomer()
            call.respondText(Json.encodeToString(Devroom(1565160000005556495)))
        } catch (e: Exception) {
            call.respondText(e.toString())
            call.respond(HttpStatusCode.BadRequest)
        }
        call.respond(HttpStatusCode.OK)
    }
}

