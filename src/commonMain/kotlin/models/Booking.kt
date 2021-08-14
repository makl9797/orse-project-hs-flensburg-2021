package models

import dev.fritz2.lenses.IdProvider
import dev.fritz2.lenses.Lenses
import dev.fritz2.resource.Resource
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Serializable
@Lenses
data class Booking(
    val price: Double,// = 0.0,
    val customer: Customer,// = Customer(),
    val startTime: String,// = "01-01-1970",
    val endTime: String,// = "01-01-1970",
    val subject: Subject// = Subject()
)

object BookingResource : Resource<Booking, String> {
    override val idProvider: IdProvider<Booking, String> = Booking::startTime

    override fun deserialize(source: String): Booking = Json.decodeFromString(Booking.serializer(), source)
    override fun deserializeList(source: String): List<Booking> =
        Json.decodeFromString(ListSerializer(Booking.serializer()), source)

    override fun serialize(item: Booking): String = Json.encodeToString(Booking.serializer(), item)
    override fun serializeList(items: List<Booking>): String =
        Json.encodeToString(ListSerializer(Booking.serializer()), items)
}
