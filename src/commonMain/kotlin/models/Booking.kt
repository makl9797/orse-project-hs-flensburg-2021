package models
import kotlinx.serialization.Serializable
import kotlinx.datetime.*
@Serializable
data class Booking(
    val price: Double,
    val customer: Customer,
    val startTime: String,
    val endTime:String,
    val subject: Subject
)
