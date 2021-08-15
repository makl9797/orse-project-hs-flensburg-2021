package models

import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
class Customer : Person {
    var _id = ""

    @Contextual
    var address = Address("", "", 0)

    constructor(_id: String, address: Address, firstname: String, lastname: String) : super(
        firstname,
        lastname
    ) {
        this._id = _id
        this.address = address
    }
}

@Lenses
data class CustomerLenses(
    val _id: String,
    val address: Address,
    val firstname: String,
    val lastname: String
)