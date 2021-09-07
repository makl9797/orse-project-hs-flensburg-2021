package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.datetime.*
import models.data.Booking
import models.data.Day
import models.data.Subject

/**
 * Here is the implementation of the Routes of Overview
 */
fun Route.overviewRoutes() {

    /**
     * get(/overview) delivers a List of Days upto "period",
     * containing the bookings and available subjects of each day.
     * If something went wrong we catch it and send a report
     */
    get("/overview") {
        try {
            var period = call.parameters["periodInDays"]?.toInt()
            if (period == null) {
                period = 365
            }
            val listOfDays = createDayList(period)

            call.respond(listOfDays)

        } catch (e: Exception) {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("Error_ $e")
        }
    }
}

/**
 * createDayList @returns a mutable list of days containing available subjects und bookings of each day
 */
fun createDayList(period: Int): MutableList<Day> {
    val today = Clock.System.todayAt(TimeZone.currentSystemDefault())       // get current date
    val endOfTimeFrame = today.plus(DatePeriod(days = period))              // get last day of timeframe
    val bookingsInTimeframe = getBookingsInTimeframe(today, endOfTimeFrame) // get bookings in the current timeframe
    val daylist = mutableListOf<Day>()                                      // create an empty list of days
    val allSubjects = getAllSubjects()                                      // get a list of all subjects
    for (i in 0..period) {
        val nextDay = today.plus((DatePeriod(days = i)))                    // get the next
        val bookingsOfDay = getBookingsOfDay(nextDay, bookingsInTimeframe)  // get bookings of a day
        val availableSubjects =
            getAvailableSubjectsOfDay(allSubjects.toMutableList(), bookingsOfDay) // get available subjects of a day

        daylist.add( // create a Day
            Day(
                nextDay.toString(),
                bookingsOfDay,
                availableSubjects,
                availableSubjects.count()
            )
        )
    }
    return daylist
}

/**
 * @return a list of Bookings on a specific day
 */
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

/**
 * @return a list of Bookings in a given timeframe
 */
fun getBookingsInTimeframe(start: LocalDate, end: LocalDate): List<Booking> {
    val allBookings = databaseService.getCollectionOfBooking().find().toList()
    val bookingsInTimeFrame = mutableListOf<Booking>()
    allBookings.forEach { booking ->
        if (booking.startTime.toLocalDate() in start..end || booking.endTime.toLocalDate() in start..end)
            bookingsInTimeFrame.add(booking)
    }
    return bookingsInTimeFrame
}

/**
 * @return a list of all subjects
 */
fun getAllSubjects(): List<Subject> {
    return databaseService.getCollectionOfSubject().find().toList()
}

/**
 * @return a list of subjects that are available at a specific day
 */
fun getAvailableSubjectsOfDay(
    subjects: MutableList<Subject>,
    bookingsOfDay: MutableList<Booking>
): MutableList<Subject> {

    val notAvailableSubjectIds = mutableListOf<String>()
    val subjectsBooked = mutableListOf<Subject>()
    val availableSubjects = mutableListOf<Subject>()

    bookingsOfDay.forEach { item -> subjectsBooked.add(item.subject!!) }

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
