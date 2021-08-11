package models

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
