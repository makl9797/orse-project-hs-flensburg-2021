package models

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