package models

import dev.fritz2.lenses.IdProvider
import dev.fritz2.lenses.Lenses
import dev.fritz2.resource.Resource
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
@Lenses
data class Appointment(
    val appointmentId: String,
    val date: Long,
    val info: String
)

object AppointmentResource : Resource<Appointment, String> {
    override val idProvider: IdProvider<Appointment, String> = Appointment::appointmentId
    override fun deserialize(source: String): Appointment = Json.decodeFromString(Appointment.serializer(), source)
    override fun serialize(item: Appointment): String = Json.encodeToString(Appointment.serializer(), item)

}