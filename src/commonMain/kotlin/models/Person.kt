package models

import kotlinx.serialization.Serializable

@Serializable
open class Person {
    var firstname = ""
    var lastname = ""

    constructor(fname: String, lname: String) {
        this.firstname = fname
        this.lastname = lname
    }
}