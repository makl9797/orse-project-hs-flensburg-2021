package models

import com.benasher44.uuid.Uuid
import dev.fritz2.lenses.Lenses

@Lenses
data class Address(
    val uuid: Uuid,
    val street: String,
    val city: String,
    val zip: Int
)
