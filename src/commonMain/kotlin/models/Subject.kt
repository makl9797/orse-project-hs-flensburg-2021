package models

import dev.fritz2.identification.uniqueId
import dev.fritz2.lenses.IdProvider
import dev.fritz2.lenses.Lenses
import dev.fritz2.resource.Resource
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Serializable
class Subject {
    var subjectName = ""
    var subjectDescription = ""
    var _id = uniqueId()

    constructor() : super() {}
    constructor(subjectName: String, subjectDescription: String, _id: String) {
        this.subjectName = subjectName
        this.subjectDescription = subjectDescription
        this._id = _id
    }

}

@Lenses
@Serializable
data class SubjectLenses(
    val subjectName: String,
    val subjectDescription: String,
    val _id: String
)


object SubjectResource : Resource<SubjectLenses, String> {
    override val idProvider: IdProvider<SubjectLenses, String> = SubjectLenses::_id

    override fun deserialize(source: String): SubjectLenses = Json.decodeFromString(SubjectLenses.serializer(), source)
    override fun deserializeList(source: String): List<SubjectLenses> =
        Json.decodeFromString(ListSerializer(SubjectLenses.serializer()), source)

    override fun serialize(item: SubjectLenses): String = Json.encodeToString(SubjectLenses.serializer(), item)
    override fun serializeList(items: List<SubjectLenses>): String =
        Json.encodeToString(ListSerializer(SubjectLenses.serializer()), items)
}