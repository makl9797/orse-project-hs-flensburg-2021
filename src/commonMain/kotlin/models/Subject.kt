package models

import dev.fritz2.identification.uniqueId
import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Serializable

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
