package models

import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Serializable

@Serializable
class Subject {
    var subjectName = ""
    var subjectSubscription = ""
    var subjectId = ""

    constructor() : super() {}
    constructor(subjectName: String, subjectSubscription: String, subjectId: String) {
        this.subjectName = subjectName
        this.subjectSubscription = subjectSubscription
        this.subjectId = subjectId
    }

}

@Lenses
data class SubjectLenses(
    val subjectName: String,
    val subjectSubscription: String,
    val subjectId: String
)