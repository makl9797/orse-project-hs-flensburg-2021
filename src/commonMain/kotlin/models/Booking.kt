package models
import kotlinx.serialization.Serializable
import kotlin.time.*
@Serializable
data class Booking(
    val price: Double,
    val customer: Customer,
    val startTime: Long,
    val endTime:Long,
    val subject: Subject
)
