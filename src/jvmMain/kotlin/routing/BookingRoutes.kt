package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.data.Booking
import org.litote.kmongo.eq
import org.litote.kmongo.findOneById
import org.litote.kmongo.save

/**
 * Here is the implementation of the Routes of Booking
 */
fun Route.bookingRoutes() {
    /**
     * get(/bookings) delivers every booking on a specific day
     */
    get("/bookings") {
        try {
            val col = databaseService.getCollectionOfBooking()  // get collection of Booking
            val day = call.request.queryParameters["day"]       // extract day by parameters
            var bookings = col.find().toList()                  // get a list of all bookings
            if (day != null) {
                bookings = col.find(Booking::startTime eq day).toList() // filter bookings by day
            }
            call.respond(bookings)                              // respond the result
        } catch (e: Exception) {                                // if something went wrong we catch it and respond report
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }
    /**
     * get(bookings/id) delivers a booking by id
     */
    get("/bookings/{id}") {
        try {
            val col = databaseService.getCollectionOfBooking()   // get collection of Booking
            val id = call.parameters["id"].toString()            // get the transmitted id
            val booking = col.findOneById(id)                   // find the booking with by id
            if (booking != null) {
                call.respond(booking)                           // respond the result
            } else {
                call.respondText("Booking with _id:$id not found")
            }
        } catch (e: Exception) {                                 // if something went wrong we catch it and respond report
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }
    /**
     * put(bookings/id) create or update a booking by id
     */
    put("/bookings/{id}") {
        try {
            val booking = call.receive<Booking>()               // expect a Booking
            val bookingId = call.parameters["id"].toString()    // get the transmitted id
            booking._id = bookingId                             // set transmitted id to booking id
            val col = databaseService.getCollectionOfBooking()  // get collection of Booking
            col.save(booking)                                   // save the booking in collection

            call.respond(col.find().toList())                   // respond all bookings

        } catch (e: Exception) {                                 // if something went wrong we catch it and respond report
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }
    /**
     * delete(bookins/id) delete a booking by id
     */
    delete("/bookings/{id}") {
        try {
            val col = databaseService.getCollectionOfBooking()          // get collection of Booking
            col.deleteOne(Booking::_id eq call.parameters["id"])   // delete Booking with the given id
            call.respond(col.find().toList())                           // respond all bookings

        } catch (e: Exception) {                                // if something went wrong we catch it and respond report
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }
}