package util

import dev.fritz2.lenses.Lenses
import kotlinx.serialization.Serializable

@Serializable
class UTime {
    var timestamp = 0.toLong()

    constructor() : super() {}
    constructor(timestamp: Long) {
        this.timestamp = timestamp
    }

}

@Lenses
data class UTimeLenses(
    val timestamp: Long
)