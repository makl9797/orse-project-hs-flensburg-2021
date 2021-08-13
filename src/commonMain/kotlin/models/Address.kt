package models


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