package models


import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDateTime

data class Appointment(val uuid: Uuid, val date: LocalDateTime, val info: String)