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

    println("ausgabe_ $today $endOfTimeFrame")
    return getBookingsInTimeframe(today, endOfTimeFrame)//1628812800, 1629072060
}



fun getBookingsOfDay(day: Int, bookings: List<Booking>): List<Booking> {
    return emptyList()
}

fun getBookingsInTimeframe(start: LocalDate, end: LocalDate): List<Booking> {
    val allBookings = databaseService.getCollectionOfBooking().find().toList()
    val bookingsInTimeFrame = mutableListOf<Booking>()
    allBookings.forEach { booking ->

    }
    return bookingsInTimeFrame
}

fun getAllSubjects(): List<Subject> {
    return emptyList()
}

