package models.data

import dev.fritz2.identification.uniqueId
import dev.fritz2.lenses.IdProvider
import dev.fritz2.lenses.Lenses
import dev.fritz2.resource.Resource
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

/**
 * This data class acts as Subject, every Subject has a unique id
 */
@Lenses
@Serializable
data class Subject(
    var _id: String = uniqueId(),
    val name: String,
    val description: String,
    val type: String
)
/**
 * SubjectResource is used to serialize and deserialize a Subject or a List of Subjects
 */
object SubjectResource : Resource<Subject, String> {
    override val idProvider: IdProvider<Subject, String> = Subject::_id

    override fun deserialize(source: String): Subject = Json.decodeFromString(Subject.serializer(), source)
    override fun deserializeList(source: String): List<Subject> =
        Json.decodeFromString(ListSerializer(Subject.serializer()), source)

    override fun serialize(item: Subject): String = Json.encodeToString(Subject.serializer(), item)
    override fun serializeList(items: List<Subject>): String =
        Json.encodeToString(ListSerializer(Subject.serializer()), items)
}