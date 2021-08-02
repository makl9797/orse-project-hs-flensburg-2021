package models

import com.benasher44.uuid.Uuid

data class Person(val firstname: String, val lastname: String, val addressId: Uuid)
