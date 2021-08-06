package models

import kotlinx.serialization.Serializable

@Serializable
data class Subject(val subjectName: String , val subjectSubscription: String, val subjectId: String)
