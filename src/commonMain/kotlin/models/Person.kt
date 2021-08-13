package models
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