package routing

import com.benasher44.uuid.Uuid
import com.mongodb.client.MongoCollection
import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Booking
import models.Subject


fun Route.subjectRoutes() {
    get("/subject/available") {
        try {
            val start = call.parameters["start"]
            val end = call.parameters["end"]
            val subjectsCol = databaseService.getCollectionOfSubject()
            val bookingsCol = databaseService.getCollectionOfBooking()

            val availableSubjects =
                start?.let { it1 ->
                    end?.let { it2 ->
                        getAvailableSubjectsInTimeframe(
                            it1.toLocalDate(),
                            it2.toLocalDate(), subjectsCol, bookingsCol
                        )
                    }
                }

            call.respondText(Json.encodeToString(availableSubjects))
            call.respond(HttpStatusCode.OK)
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

}

fun getAvailableSubjectsInTimeframe(
    start: LocalDate,
    end: LocalDate,
    subjects: MongoCollection<Subject>,
    bookings: MongoCollection<Booking>,
): MutableList<Subject> {
    val notAvailableSubjectIds = mutableListOf<String>()
    val availableSubjects = mutableListOf<Subject>()
    val allSubjects = subjects.find().toList()
    for (booking: Booking in bookings.find().toList()) {
        if (booking.startTime.toLocalDate() in start..end || booking.endTime.toLocalDate() in start..end) {
            notAvailableSubjectIds.add(booking.subject.subjectId)
        }
    }
    allSubjects.forEach { subject ->
        if (subject.subjectId !in notAvailableSubjectIds) {
            availableSubjects.add(subject)
        }
    }
    return availableSubjects
}

