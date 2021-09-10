package modules

import models.app.Module
import models.app.ModuleCard
import models.app.ModuleSettings
import modules.calendar.Calendar
import modules.info.InfoBox
import modules.table.Table


/**
 * ModuleCatalog
 *
 * Contains every Module-Type
 */
class ModuleCatalog {
    val infoBox: InfoBox = InfoBox()
    val table: Table = Table()
    val calendar: Calendar = Calendar()

    val defaultModules = listOf(
        infoBox.createModule(),
        table.createModule(),
        calendar.createModule()
    )

    /**
     * Creates a new Module
     */
    fun newModule(type: Module.Type, settings: ModuleSettings, card: ModuleCard): Module {
        return when (type) {
            Module.Type.INFOBOX -> {
                infoBox.createModule(settings, card)
            }
            Module.Type.TABLE -> {
                table.createModule(settings, card)
            }
            Module.Type.CALENDAR -> {
                calendar.createModule(settings, card)
            }
            else -> {
                throw Exception()
            }
        }
    }
}


val moduleCatalog = ModuleCatalog()