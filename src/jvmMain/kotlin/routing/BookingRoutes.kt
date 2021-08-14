package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.Booking
import org.litote.kmongo.eq

fun Route.bookingRoutes() {
    get("/bookings") {
        try {
            val col = databaseService.getCollectionOfBooking()
            val bookings = col.find().toList()
            call.respond(bookings)
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    post("/bookings/{id}") {
        try {
            val booking = call.receive<Booking>()
            val bookingId = call.parameters["id"].toString()
            booking.id = bookingId
            val col = databaseService.getCollectionOfBooking()
            col.insertOne(booking)
            call.respond(col.find().toList())
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    put("/bookings/{id}") {
        try {
            val booking = call.receive<Booking>()
            val bookingId = call.parameters["id"].toString()
            booking.id = bookingId
            val col = databaseService.getCollectionOfBooking()
            col.findOneAndReplace(Booking::id eq bookingId, booking)
            call.respond(col.find().toList())
            call.respond(HttpStatusCode.OK)

        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    delete("/bookings/{id}") {
        try {
            val col = databaseService.getCollectionOfBooking()
            col.deleteOne(Booking::id eq call.parameters["id"].toString())
            call.respond(col.find().toList())
            call.respond(HttpStatusCode.OK)

        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}