package models

import com.benasher44.uuid.Uuid
import kotlinx.serialization.Serializable

@Serializable
data class Appointment(val appointmentId: String, val date: Long, val info: String)