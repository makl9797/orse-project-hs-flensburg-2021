package models

import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Serializable

@Lenses
@Serializable
data class Day(
    val day: String,
    val bookings: MutableList<Booking>,
    val subjects: MutableList<Subject>
)