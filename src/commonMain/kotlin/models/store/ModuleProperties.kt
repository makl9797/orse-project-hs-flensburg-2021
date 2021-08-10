package models.store

import dev.fritz2.lenses.Lenses

@Lenses
data class ModuleProperties(
    val card: ModuleCard,
    val settings: ModuleSettings
)
