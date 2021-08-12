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

import java.time.*


fun Route.customerRoute() {
    post("/customer/create") {
        try {
            //val customer = Customer("CUSTOMER_ID")
            //val col = databaseService.getCollectionOfCustomer()
            val date = LocalDate(2021, 8, 16)

            val date1 = "2021-08-16".toLocalDate()
            call.respondText(Json.encodeToString(date)+Json.encodeToString(date1))

        } catch (e: Exception) {
            call.respondText(e.toString())
            call.respond(HttpStatusCode.BadRequest)
        }
        call.respond(HttpStatusCode.OK)
    }
}


