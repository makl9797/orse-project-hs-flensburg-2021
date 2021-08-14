package models


import kotlinx.serialization.Serializable

@Serializable
class Customer : Person {
    var customerId = ""

    constructor(customer_id: String, fname: String, lname: String) : super(fname, lname) {
        this.customerId = customer_id
    }
}