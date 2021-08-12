package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.datetime.toLocalDate
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Booking
import org.litote.kmongo.findOne
import java.sql.Timestamp
import kotlin.time.*

fun Route.bookingRoutes() {
    post("/booking") {
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
            call.respondText(Json.encodeToString(bookings))
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    get("/booking") {
        try {
            val booking= databaseService.getCollectionOfBooking().findOne()
            val date = booking?.startTime?.toLocalDate()
            if (date != null) {
                call.respondText(date.dayOfMonth.toString())
            }
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}