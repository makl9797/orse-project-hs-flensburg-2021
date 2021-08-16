package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.Customer
import org.litote.kmongo.eq


fun Route.customerRoute() {
    post("/customer/create") {
        try {
            databaseService.getCollectionOfCustomer().insertOne(call.receive<Customer>())
            call.respond(HttpStatusCode.Created)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    get("/customer/search") {
        try {
            call.respond(
                databaseService.getCollectionOfCustomer().find(Customer::lastname eq call.parameters["name"]).toList()
            )
            call.respond(HttpStatusCode.OK)

        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}


