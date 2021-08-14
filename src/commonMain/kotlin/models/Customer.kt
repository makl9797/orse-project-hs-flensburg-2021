package models

import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
class Customer : Person {
    var customerId = ""

    @Contextual
    var address = Address("", "", 0)

    constructor(customerId: String, address: Address, firstname: String, lastname: String) : super(
        firstname,
        lastname
    ) {
        this.customerId = customerId
        this.address = address
    }
}

@Lenses
data class CustomerLenses(
    val customerId: String,
    val address: Address,
    val firstname: String,
    val lastname: String
)