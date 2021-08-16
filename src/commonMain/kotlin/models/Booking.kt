package models

import dev.fritz2.identification.uniqueId
import dev.fritz2.lenses.IdProvider
import dev.fritz2.lenses.Lenses
import dev.fritz2.resource.Resource
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Serializable
@Lenses
data class Booking(
    var _id: String = uniqueId(),
    val price: Double,
    val customer: Customer,
    val startTime: String,
    val endTime: String,
    val subject: Subject
)

object BookingResource : Resource<Booking, String> {
    override val idProvider: IdProvider<Booking, String> = Booking::_id

    override fun deserialize(source: String): Booking = Json.decodeFromString(Booking.serializer(), source)
    override fun deserializeList(source: String): List<Booking> =
        Json.decodeFromString(ListSerializer(Booking.serializer()), source)

    override fun serialize(item: Booking): String = Json.encodeToString(Booking.serializer(), item)
    override fun serializeList(items: List<Booking>): String =
        Json.encodeToString(ListSerializer(Booking.serializer()), items)
}
