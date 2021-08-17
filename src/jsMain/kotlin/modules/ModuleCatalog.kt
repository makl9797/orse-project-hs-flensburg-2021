package modules

import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings
import modules.info.InfoBox
import modules.table.Table

class ModuleCatalog {
    private val infoBox: InfoBox = InfoBox()
    val table: Table = Table()

    val defaultModules = listOf(
        infoBox.createModule(),
        table.createModule(),
    )

    fun newModule(type: Module.Type, settings: ModuleSettings, card: ModuleCard): Module {
        return when (type) {
            Module.Type.INFOBOX -> {
                infoBox.createModule(settings, card)
            }
            Module.Type.TABLE -> {
                table.createModule(settings, card)
            }
            else -> {
                throw Exception()
            }
        }
    }
}


val moduleCatalog = ModuleCatalog()