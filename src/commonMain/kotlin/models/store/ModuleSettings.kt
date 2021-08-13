package models.store

import dev.fritz2.lenses.Lenses

@Lenses
data class ModuleSettings(
    var width: Int = 16,
    var height: Int = 9,
    var startX: Int = 1,
    var startY: Int = 1
)
