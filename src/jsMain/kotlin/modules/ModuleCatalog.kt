package modules

import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings
import modules.calendar.Calendar
import modules.info.InfoBox

class ModuleCatalog() {
    private val infoBox: InfoBox = InfoBox()
    private val calendar: Calendar = Calendar()
    val defaultModules = listOf(
        infoBox.createModule(),
        calendar.createModule()
    )

    fun newModule(type: Module.Type, settings: ModuleSettings, card: ModuleCard): Module {
        return when (type) {
            Module.Type.INFOBOX -> {
                infoBox.createModule(settings, card)
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