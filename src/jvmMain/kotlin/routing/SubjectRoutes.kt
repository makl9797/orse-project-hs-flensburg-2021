package routing

import com.benasher44.uuid.Uuid
import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import models.Booking
import models.Subject
import org.litote.kmongo.eq
import org.litote.kmongo.findOne


fun Route.subjectRoutes() {
    get("/subject/available") {
        try {
            val start = call.parameters["start"]
            val end = call.parameters["end"]
            val subjects = databaseService.getCollectionOfSubject().find().toList()
            val bookings = databaseService.getCollectionOfBooking().find().toList()

            val availableSubjects =
                start?.let { it1 ->
                    end?.let { it2 ->
                        getAvailableSubjectsInTimeframe(
                            it1.toLocalDate(),
                            it2.toLocalDate(), subjects, bookings
                        )
                    }
                }

            if (availableSubjects != null) {
                call.respond(availableSubjects.toList())
            } else {
                throw Exception("No available Subjects")
            }
            call.respond(HttpStatusCode.Created)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    post("/subject/create") {
        try {
            val subject =
                call.parameters["subscription"]?.let { it2 ->
                    call.parameters["name"]?.let { it3 ->
                        Subject(
                            it3,
                            it2,
                            Uuid.randomUUID().toString()
                        )
                    }
                }
            val col = databaseService.getCollectionOfSubject()
            col.insertOne(subject)
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    get("/subject/{id}") {
        try {
            val id = call.parameters["id"]
            val subject = databaseService.getCollectionOfSubject().findOne(Subject::subjectId eq id)
            if (subject != null) {
                call.respond(subject)
                call.respond(HttpStatusCode.OK)
            } else {
                call.respondText { "no subject was found with the ID $id" }
            }
        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }


    }

}

fun getAvailableSubjectsInTimeframe(
    start: LocalDate,
    end: LocalDate,
    subjects: List<Subject>,
    bookings: List<Booking>,
): MutableList<Subject> {
    val notAvailableSubjectIds = mutableListOf<String>()
    val availableSubjects = mutableListOf<Subject>()
    for (booking: Booking in bookings) {
        if (booking.startTime.toLocalDate() in start..end || booking.endTime.toLocalDate() in start..end) {
            notAvailableSubjectIds.add(booking.subject.subjectId)
        }
    }
    subjects.forEach { subject ->
        if (subject.subjectId !in notAvailableSubjectIds) {
            availableSubjects.add(subject)
        }
    }
    return availableSubjects
}

