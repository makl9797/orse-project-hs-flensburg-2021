package routing
import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Booking
import models.Customer


fun Route.bookingRoutes() {
    post("/booking") {
        try {
            val booking = call.receive<Booking>()
            // val booking = Booking(5.55, Customer("MY_CUSTOMER_ID"), UTime(12323213123131232))
            val col = databaseService.getCollectionOfBooking()
            col.insertOne(booking)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
        call.respond(HttpStatusCode.OK)
    }
    get("/booking/all") {
        try {
            val col = databaseService.getCollectionOfBooking()
            val bookings = col.find().toList()
            call.respondText(Json.encodeToString(bookings))
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
        }
    }
}