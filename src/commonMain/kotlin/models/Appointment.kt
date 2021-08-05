package models

import com.benasher44.uuid.Uuid


data class Appointment(val appointmentId: Uuid, val date: Long, val info: String)