package routing

import databaseService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import models.*
import java.sql.Timestamp
import java.time.*


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
    val now = LocalDateTime.now()
    val currentDay = now.dayOfMonth

    val start = LocalDateTime.of(2021, Month.AUGUST, 13, 0, 0)
    val end = LocalDateTime.of(2021, Month.AUGUST, 16, 0, 1)
    val t = Timestamp.valueOf(start).time
    val e = Timestamp.valueOf(end).time
    println("ausgabe_ $t $e")
    return getBookingsInTimeframe(t,e)//1628812800, 1629072060
}

fun getBookingsOfDay(day: Int, month: Month, bookings: List<Booking>): List<Booking> {
    return emptyList()
}

fun getBookingsInTimeframe(timestampOfStartDay: Long, timestampOfEndDay: Long): List<Booking> {
    val allBookings = databaseService.getCollectionOfBooking().find().toList()
    val bookingsInTimeFrame = mutableListOf<Booking>()
    allBookings.forEach { booking ->
       // println("ausgabe_ " + " startBooking: " + booking.startTime + " endBooking: " + booking.endTime + " || " + " startTimestamp: " + timestampOfStartDay + " endTimestamp: " + timestampOfEndDay)
        if (booking.endTime in timestampOfStartDay..timestampOfEndDay || booking.startTime in timestampOfStartDay..timestampOfEndDay) {
            bookingsInTimeFrame.add(booking)
        }
    }
    return bookingsInTimeFrame
}

fun getAllSubjects(): List<Subject> {
    return emptyList()
}

