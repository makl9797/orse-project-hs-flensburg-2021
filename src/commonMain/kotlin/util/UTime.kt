package util

import kotlinx.serialization.Serializable

@Serializable
class UTime {
    var timestamp = 0.toLong()

    constructor() : super() {}
    constructor(timestamp: Long) {
        this.timestamp = timestamp
    }

}