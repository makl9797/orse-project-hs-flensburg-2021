package models

import com.benasher44.uuid.Uuid
import kotlinx.serialization.Serializable

@Serializable
data class Customer(val CustomerId: String)
