package models.data

import dev.fritz2.identification.uniqueId
import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Serializable

/**
 * This data class acts as Address, every Address has a unique id
 */
@Lenses
@Serializable
data class Address(
    val _id: String = uniqueId(),
    val street: String,
    val city: String,
    val zip: Int
)