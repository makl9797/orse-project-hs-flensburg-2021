package routing

import com.benasher44.uuid.Uuid
import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import models.Appointment
import org.litote.kmongo.eq
import org.litote.kmongo.findOne


fun Route.appointmentRoutes() {
    get("/appointment") {
        val id = call.parameters["id"]
        val appointmentCollection = databaseService.getCollectionOfAppointment()
        val appointment =
            appointmentCollection.findOne(Appointment::appointmentId eq id)
        if (appointment != null) {
            call.respond(appointment)
        }
        call.respond(HttpStatusCode.BadRequest)
    }
    post("/appointment") {
        val date = call.parameters["date"]
        if (date != null) {
            try {
                val longValue = date.toLong()
                databaseService.getCollectionOfAppointment()
                    .insertOne(Appointment(Uuid.randomUUID().toString(), longValue, call.parameters["info"].toString()))
                call.respond(HttpStatusCode.Created)
            } catch (e: NumberFormatException) {
                call.respondText("It seems as if the date field does not hold a valid value")
            }

        }
    }
    delete("/appointment") {
        val id = call.parameters["id"] ?: error("Invalid delete request")
        val appointmentCollection = databaseService.getCollectionOfAppointment()
        appointmentCollection.deleteOne(Appointment::appointmentId eq id)
        call.respond(HttpStatusCode.OK)
    }
}