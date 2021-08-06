package routing

import databaseService
import de.undercouch.bson4jackson.types.ObjectId
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Booking
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.findOneById

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
    get("/booking/all") {
        try {
            val col = databaseService.getCollectionOfBooking()
            val booking = col.find()

            call.respondText(Json.encodeToString(booking))
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
        }
    }
}