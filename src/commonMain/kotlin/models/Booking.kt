package models
import UTime
import kotlinx.serialization.Serializable

@Serializable
data class Booking(
    val price: Double,
    val customer: Customer,
    val startTime: Long,
    val endTime:Long,
    val subject: Subject
)
