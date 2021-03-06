package models.app

import dev.fritz2.lenses.Lenses

@Lenses
data class Module(
    var id: String,
    val type: Type,
    val settings: ModuleSettings,
    val card: ModuleCard
) {
    enum class Type {
        CALENDAR, INFOBOX, TABLE
    }
}
