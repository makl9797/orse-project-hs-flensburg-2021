package models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Booking(val endTime: LocalDate, val customer: Customer, val price: Float, val subjectId: String)
