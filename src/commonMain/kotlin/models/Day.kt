package models

import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Serializable

@Serializable
class Day {
    var day = ""
    var availableSubjects = mutableListOf<Subject>()
    var bookingList = mutableListOf<Booking>()

    constructor() : super() {}
    constructor(day: String, bookings: MutableList<Booking>, subjects: MutableList<Subject>) {
        this.day = day
        this.availableSubjects = subjects
        this.bookingList = bookings
    }
}

@Lenses
data class DayLenses(
    val day: String,
    val bookings: MutableList<Booking>,
    val subjects: MutableList<Subject>
)