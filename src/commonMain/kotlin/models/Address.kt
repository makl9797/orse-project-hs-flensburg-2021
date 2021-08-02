package models

import com.benasher44.uuid.Uuid

data class Address(val uuid: Uuid, val street: String, val city: String, val zip: Int)
