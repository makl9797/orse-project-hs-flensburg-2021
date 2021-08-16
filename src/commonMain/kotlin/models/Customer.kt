package models

import dev.fritz2.identification.uniqueId
import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Serializable


@Lenses
@Serializable
data class Customer(
    val _id: String = uniqueId(),
    val address: Address,
    val firstname: String,
    val lastname: String
)