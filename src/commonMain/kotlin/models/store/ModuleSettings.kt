package models.store

import dev.fritz2.lenses.Lenses

@Lenses
data class ModuleSettings(
    var width: Double,
    var height: Double,
    var parentX: Double,
    var parentY: Double
)
