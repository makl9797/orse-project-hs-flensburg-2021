package models

import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Serializable

@Serializable
@Lenses
data class Appointment(
    val appointmentId: String,
    val date: Long,
    val info: String
)