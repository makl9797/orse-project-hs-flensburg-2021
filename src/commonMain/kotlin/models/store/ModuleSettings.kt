package models.store

import dev.fritz2.lenses.Lenses

@Lenses
data class ModuleSettings(
    var width: Double = 150.0,
    var height: Double = 150.0,
    var parentX: Double = 0.0,
    var parentY: Double = 0.0
)
