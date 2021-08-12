package models

import kotlinx.serialization.Serializable

@Serializable
class Day {
    var timestampOfThisDay = 0.toLong()
    var subjectList = mutableListOf<Subject>()
    var bookingList = mutableListOf<Booking>()

    constructor() : super() {}
    constructor(timestamp: Long, subjects: MutableList<Subject>, bookings: MutableList<Booking>) {
        this.timestampOfThisDay = timestamp
        this.subjectList = subjects
        this.bookingList = bookings
    }
}