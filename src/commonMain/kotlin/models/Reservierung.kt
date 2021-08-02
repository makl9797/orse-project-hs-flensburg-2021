package models

import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDateTime


data class Reservierung(val endZeit : LocalDateTime, val preis: Float,  val objektNr: Uuid )
