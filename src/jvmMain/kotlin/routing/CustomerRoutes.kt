package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.data.Customer
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.save

// Here is the implementation of the Routes of Customer
fun Route.customerRoute() {
    // update a customer based on a given ID, or create a new one
    put("/customers/{id}") {
        try {
            val customers = databaseService.getCollectionOfCustomer()
            val customer = call.receive<Customer>()
            val customerId = call.parameters["id"]
            if (customerId != null) {
                customer._id = customerId
            }
            customers.save(customer)
            call.respond(customers.find().toList())

        } catch (e: Exception) {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }
    // Delete a customer based on a given ID or return an Error 400
    delete("/customers/{id}") {
        try {
            val customers = databaseService.getCollectionOfCustomer()
            customers.deleteOne(Customer::_id eq call.parameters["id"])
            call.respond(customers.find().toList())
        } catch (e: Exception) {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }
    // Get a customer based on a given ID or return an Error 400
    get("/customers/{id}") {
        try {
            val customerId = call.parameters["id"]
            val customers = databaseService.getCollectionOfCustomer()

            val customer = customers.findOne(Customer::_id eq customerId)
            if (customer != null) {
                call.respond(Json.encodeToString(customer))
            } else {
                call.response.status(HttpStatusCode.BadRequest)
                call.respondText("no customer with the id $customerId was found")
            }


        } catch (e: Exception) {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }
    // Get all customers from table customers
    get("/customers") {
        try {
            val customers = databaseService.getCollectionOfCustomer()
            call.respond(customers.find().toList())
        } catch (e: Exception) {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }
}


