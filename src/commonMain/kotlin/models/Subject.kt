package models

import com.benasher44.uuid.Uuid
import kotlinx.serialization.Serializable

@Serializable
data class Subject(val subjectName: String , val subjectSubscription: String, val subjectId: String)
