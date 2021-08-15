package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import models.Booking
import models.Subject
import org.litote.kmongo.findOneById


fun Route.subjectRoutes() {
    get("/subjects/{id}") {
        try {
            val subjects = databaseService.getCollectionOfSubject()
            val id = call.parameters["id"].toString()
            val subject = subjects.findOneById(id)
            if (subject != null) {
                call.respond(subject)
            } else {
                call.respondText("Subject with _id:$id not found")
            }
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    get("/subjects") {
        try {
            val onlyAvailable: Boolean = call.parameters["onlyAvailable"].toBoolean()
            val startTime = call.parameters["start"]
            val endTime = call.parameters["end"]
            val subjects = databaseService.getCollectionOfSubject()
            val bookings = databaseService.getCollectionOfBooking()
            if (startTime != null && endTime != null) {
                call.respond(
                    getSubjectsInTimeframe(
                        startTime.toLocalDate(),
                        endTime.toLocalDate(),
                        subjects.find().toList(),
                        bookings.find().toList(),
                        onlyAvailable
                    )
                )
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(subjects.find().toList())
                call.respond(HttpStatusCode.OK)
            }
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    post("/subjects") {
        try {
            val subject = call.receive<Subject>()
            val subjects = databaseService.getCollectionOfSubject()
            subjects.insertOne(subject)
            call.respond(subjects.find().toList())
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }

}

fun getSubjectsInTimeframe(
    start: LocalDate,
    end: LocalDate,
    subjects: List<Subject>,
    bookings: List<Booking>,
    onlyAvailable: Boolean
): MutableList<Subject> {
    val notAvailableSubjectIds = mutableListOf<String>()
    val notAvailableSubjects = mutableListOf<Subject>()
    val availableSubjects = mutableListOf<Subject>()
    for (booking: Booking in bookings) {
        if (booking.startTime.toLocalDate() in start..end || booking.endTime.toLocalDate() in start..end) {
            notAvailableSubjectIds.add(booking.subject._id)
        }
    }
    subjects.forEach { subject ->
        if (subject._id !in notAvailableSubjectIds) {
            availableSubjects.add(subject)
        } else {
            notAvailableSubjects.add(subject)
        }
    }
    return if (onlyAvailable) {
        availableSubjects
    } else {
        notAvailableSubjects
    }

}



