package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import models.data.Booking
import models.data.Subject
import org.litote.kmongo.eq
import org.litote.kmongo.findOneById
import org.litote.kmongo.save

/**
 * Here is the implementation of the Routes of Subjects
 */
fun Route.subjectRoutes() {
    /**
     * get(/subjects/{id}) delivers a subject based on an Id
     */
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
        } catch (e: Exception) {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }
    /**
     * get(/subjects) delivers every subjects on a specific Date
     * */
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
            } else {
                call.respond(subjects.find().toList())
            }

        } catch (e: Exception) {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }
    /**
     * put(subjects/id) create or update a subject by id
     */
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
        } catch (e: Exception) {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }
    /**
     * delete(subjects/id) delete a subject by id
     */
    delete("subjects/{id}") {
        try {
            val subjects = databaseService.getCollectionOfSubject()
            subjects.deleteOne(Subject::_id eq call.parameters["id"])
            call.respond(subjects.find().toList())
        } catch (e: Exception) {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }

}
/**
 * Get Subjects in a Time frame.
 * @return a list of subjects in a Time frame.
 */
fun getSubjectsInTimeframe(
    start: LocalDate, // define a start date
    end: LocalDate, // define an end date
    subjects: List<Subject>, // define a list of empty subjects
    bookings: List<Booking>, // define a list of empty bookings
    onlyAvailable: Boolean // define a boolean variable of only available subjects
): MutableList<Subject> {
    val notAvailableSubjectIds = mutableListOf<String>() // get collection of non available Subjects Ids
    val notAvailableSubjects = mutableListOf<Subject>() // get collection of non available Subjects
    val availableSubjects = mutableListOf<Subject>() // get collection of available Subjects
    for (booking: Booking in bookings) {
        // See if each booking is in the timestamp(start-end), then it will be saved as unavailable
        // Otherwise this booking is available and will be saved as available
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



