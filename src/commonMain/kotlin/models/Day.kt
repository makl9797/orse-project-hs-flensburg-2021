package models

import kotlinx.serialization.Serializable

@Serializable
class Day {
    var day = ""
    var bookingList = mutableListOf<Booking>()

    constructor() : super() {}
    constructor(day: String, bookings: MutableList<Booking>) {
        this.day = day
        this.bookingList = bookings
    }
}