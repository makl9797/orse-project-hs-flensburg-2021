package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import models.*
import kotlinx.datetime.*


fun Route.overviewRoutes() {
    get("/overview") {
        try {
            var period = call.parameters["periodInDays"]?.toInt()
            if (period == null) {
                period = 7
            }
            val listOfDays = createDayList(period)

            call.respondText(Json.encodeToString(listOfDays))
            call.respond(HttpStatusCode.OK)

        } catch (e: Exception) {
            call.respondText("Error_ $e")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}

fun createDayList(period: Int): List<Booking> {
    val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
    val endOfTimeFrame = today.plus(DatePeriod(days = period))
    val bookings = getBookingsInTimeframe(today, endOfTimeFrame)
    return getBookingsOfDay(LocalDate(2021, 8, 16), bookings)
}


fun getBookingsOfDay(day: LocalDate, bookingsInTimeframe: List<Booking>): List<Booking> {
    val bookingsOnDay = mutableListOf<Booking>()
    bookingsInTimeframe.forEach { booking ->
        if (booking.startTime.toLocalDate() == day || booking.endTime.toLocalDate() == day) {
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

