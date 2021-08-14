package models

import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Serializable

@Serializable
@Lenses
data class Booking(
    val price: Double,
    val customer: Customer,
    val startTime: String,
    val endTime: String,
    val subject: Subject
)
