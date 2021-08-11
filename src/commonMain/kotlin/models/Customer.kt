package models


import kotlinx.serialization.Serializable

@Serializable
class Customer {
   var customerId = ""

    constructor() : super() {}
    constructor(customer_id: String) {
        this.customerId = customer_id
    }
}