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
import org.litote.kmongo.eq
import org.litote.kmongo.findOneById
import org.litote.kmongo.save


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
            val startTime = call.parameters["start"]?.toLocalDate()
            val endTime = call.parameters["end"]?.toLocalDate()
            val subjects = databaseService.getCollectionOfSubject()
            val bookings = databaseService.getCollectionOfBooking()
            if (startTime != null && endTime != null) {
                call.respond(
                    getSubjectsInTimeframe(
                        startTime,
                        endTime,
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
    put("/subjects/{id}") {
        try {
            val subjects = databaseService.getCollectionOfSubject()
            val subject = call.receive<Subject>()
            val subjectId = call.parameters["id"]
            if (subjectId != null) {
                subject._id = subjectId
            }
            subjects.save(subject)
            call.respond(subjects.find().toList())
            call.respond(HttpStatusCode.Created)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    delete("subjects/{id}") {
        try {
            val subjects = databaseService.getCollectionOfSubject()
            subjects.deleteOne(Subject::_id eq call.parameters["id"])
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



