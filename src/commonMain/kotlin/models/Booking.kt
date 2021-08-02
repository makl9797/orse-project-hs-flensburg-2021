package models

import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDateTime


data class Booking(val endTime : LocalDateTime, val price: Float, val subjectNr: Uuid )
