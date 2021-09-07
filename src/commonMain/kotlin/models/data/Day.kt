package models.data

import dev.fritz2.lenses.IdProvider
import dev.fritz2.lenses.Lenses
import dev.fritz2.resource.Resource
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
/**
 * This data class acts as Day, every Day has a unique id
 */
@Lenses
@Serializable
data class Day(
    val day: String,
    val bookings: MutableList<Booking>,
    val subjects: MutableList<Subject>,
    val availableSubjects: Int
)
/**
 * DayResource is used to serialize and deserialize a Day or a List of Days
 */
object DayResource : Resource<Day, String> {
    override val idProvider: IdProvider<Day, String> = Day::day

    override fun deserialize(source: String): Day = Json.decodeFromString(Day.serializer(), source)
    override fun deserializeList(source: String): List<Day> =
        Json.decodeFromString(ListSerializer(Day.serializer()), source)

    override fun serialize(item: Day): String = Json.encodeToString(Day.serializer(), item)
    override fun serializeList(items: List<Day>): String =
        Json.encodeToString(ListSerializer(Day.serializer()), items)
}
