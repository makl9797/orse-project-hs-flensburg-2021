package models

import com.benasher44.uuid.Uuid
import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Serializable

@Serializable
class Address {
    var street = ""
    var city = ""
    var zip = 0

    constructor() : super() {}
    constructor(street: String, city: String, zip: Int) {
        this.street = street
        this.city = city
        this.zip = zip
    }

}

@Lenses
data class AddressLenses(
    val uuid: Uuid,
    val street: String,
    val city: String,
    val zip: Int
)