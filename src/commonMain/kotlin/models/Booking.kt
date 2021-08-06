package models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Booking(
    val startTime: LocalDate,
    val endTime: LocalDate,
    val customer: Customer,
    val price: Double,
    val subject: Subject
)
