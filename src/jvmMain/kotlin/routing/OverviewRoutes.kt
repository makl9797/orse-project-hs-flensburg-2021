package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.datetime.*
import models.Booking
import models.Day
import models.Subject


fun Route.overviewRoutes() {
    get("/overview") {
        try {
            var period = call.parameters["periodInDays"]?.toInt()
            if (period == null) {
                period = 30
            }
            val listOfDays = createDayList(period)

            call.respond(listOfDays)
            call.respond(HttpStatusCode.OK)

        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}

fun createDayList(period: Int): MutableList<Day> {
    val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
    val endOfTimeFrame = today.plus(DatePeriod(days = period))
    val bookingsInTimeframe = getBookingsInTimeframe(today, endOfTimeFrame)
    val daylist = mutableListOf<Day>()
    val allSubjects = getAllSubjects()
    for (i in 0..period) {
        val nextDay = today.plus((DatePeriod(days = i)))
        val bookingsOfDay = getBookingsOfDay(nextDay, bookingsInTimeframe)
        val availableSubjects =  getAvailableSubjectsOfDay(allSubjects.toMutableList(), bookingsOfDay)

        daylist.add(
            Day(
                nextDay.toString(),
                bookingsOfDay,
                availableSubjects,
                availableSubjects.count().toString()
            )
        )
    }
    return daylist
}


fun getBookingsOfDay(day: LocalDate, bookingsInTimeframe: List<Booking>): MutableList<Booking> {
    val bookingsOnDay = mutableListOf<Booking>()
    bookingsInTimeframe.forEach { booking ->
        val start = booking.startTime.toLocalDate()
        val end = booking.endTime.toLocalDate()
        if (day in start..end) {
            bookingsOnDay.add(booking)
        }
    }
    return bookingsOnDay
}

fun getBookingsInTimeframe(start: LocalDate, end: LocalDate): List<Booking> {
    val allBookings = databaseService.getCollectionOfBooking().find().toList()
    val bookingsInTimeFrame = mutableListOf<Booking>()
    allBookings.forEach { booking ->
        if (booking.startTime.toLocalDate() in start..end || booking.endTime.toLocalDate() in start..end)
            bookingsInTimeFrame.add(booking)
    }
    return bookingsInTimeFrame
}

fun getAllSubjects(): List<Subject> {
    return databaseService.getCollectionOfSubject().find().toList()
}


fun getAvailableSubjectsOfDay(
    subjects: MutableList<Subject>,
    bookingsOfDay: MutableList<Booking>
): MutableList<Subject> {

    val notAvailableSubjectIds = mutableListOf<String>()
    val subjectsBooked = mutableListOf<Subject>()
    val availableSubjects = mutableListOf<Subject>()

    bookingsOfDay.forEach { item -> subjectsBooked.add(item.subject) }

    subjects.forEach { item ->
        subjectsBooked.forEach { subjectBooked ->
            if (item._id in subjectBooked._id) {
                notAvailableSubjectIds.add(item._id)
            }
        }
    }
    subjects.forEach { item ->
        if (item._id !in notAvailableSubjectIds) {
            availableSubjects.add(item)
        }
    }
    return availableSubjects
}
