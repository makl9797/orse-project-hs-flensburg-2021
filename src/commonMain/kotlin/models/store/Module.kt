package models.store

import dev.fritz2.lenses.Lenses

@Lenses
data class Module(
    val id: String,
    val type: Type,
    val properties: ModuleProperties
) {
    enum class Type {
        CALENDAR, INFOBOX, TABLE
    }
}
