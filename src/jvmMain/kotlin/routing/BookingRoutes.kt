package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import models.Booking

fun Route.bookingRoutes() {
    post("/booking") {
        try {
            val booking = call.receive<Booking>()
            val col = databaseService.getCollectionOfBooking()
            col.insertOne(booking)
        } catch (e: Exception) {
            call.respondText("expect Booking, but it seems it was not a booking")
            call.respond(HttpStatusCode.BadRequest)
        }
        call.respond(HttpStatusCode.OK)
    }
    get("/booking") {
        call.respond(HttpStatusCode.OK)
    }
}