package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.Booking
import org.litote.kmongo.eq
import org.litote.kmongo.save

fun Route.bookingRoutes() {
    post("/booking/create") {
        try {
            val booking = call.receive<Booking>()
            val col = databaseService.getCollectionOfBooking()
            col.insertOne(booking)
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    get("/booking/all") {
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
    get("/booking/{day}") {
        try {
            val bookings =
                databaseService.getCollectionOfBooking().find(Booking::startTime eq call.parameters["day"]).toList()
            call.respond(bookings)
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
/*
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
    post("/bookings/") {
        try {
            val booking = call.receive<Booking>()
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
            booking.id = call.parameters["id"].toString()
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
            col.deleteOne(Booking::id eq call.parameters["id"].toString())
            call.respond(col.find().toList())
            call.respond(HttpStatusCode.OK)

        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }*/
}