package models

import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Serializable

@Serializable
open class Person {
    var firstname = ""
    var lastname = ""

    constructor(firstname: String, lastname: String) {
        this.firstname = firstname
        this.lastname = lastname
    }


}

@Lenses
data class PersonLenses(
    val firstname: String,
    val lastname: String
)