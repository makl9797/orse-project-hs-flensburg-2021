package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.Booking
import org.litote.kmongo.eq
import org.litote.kmongo.findOneById
import org.litote.kmongo.save

fun Route.bookingRoutes() {
    get("/bookings") {
        try {
            val col = databaseService.getCollectionOfBooking()
            val day = call.request.queryParameters["day"]
            var bookings = col.find().toList()
            if (day != null) {
                bookings = col.find(Booking::startTime eq day).toList()
            }
            call.respond(bookings)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    get("/bookings/{id}") {
        try {
            val col = databaseService.getCollectionOfBooking()
            val id = call.parameters["id"].toString()
            val booking = col.findOneById(id)
            if (booking != null) {
                call.respond(booking)
            } else {
                call.respondText("Booking with _id:$id not found")
            }
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    put("/bookings/{id}") {
        try {
            val booking = call.receive<Booking>()
            val bookingId = call.parameters["id"].toString()
            booking._id = bookingId
            val col = databaseService.getCollectionOfBooking()
            col.save(booking)

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
            col.deleteOne(Booking::_id eq call.parameters["id"])
            call.respond(col.find().toList())
            call.respond(HttpStatusCode.OK)

        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}