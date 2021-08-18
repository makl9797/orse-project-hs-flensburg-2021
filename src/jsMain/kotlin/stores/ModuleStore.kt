package stores

import dev.fritz2.binding.RootStore
import kotlinx.browser.document
import models.store.Module
import models.store.ModuleSettings
import modules.moduleCatalog

val moduleList =
    listOf(
        moduleCatalog.table.createModule(
            ModuleSettings(60, 30, 1, 1)
        ),
        moduleCatalog.calendar.createModule(
            ModuleSettings(30, 30, 61, 1)
        ),
        moduleCatalog.infoBox.createModule(
            ModuleSettings(60, 30, 1, 31)
        )

    )


object ModuleStore : RootStore<List<Module>>(moduleList) {
    var temp: List<Module> = listOf()

    val addModule = handle { model, newModule: Module ->
        model + moduleCatalog.newModule(newModule.type, newModule.settings, newModule.card)
    }

    val removeModule = handle { model, moduleId: String ->
        val moduleElement = document.getElementById(moduleId)
        if (moduleElement != null) {
            moduleElement.parentElement?.removeChild(moduleElement)
        }
        model.filter { module ->
            module.id != moduleId
        }
    }
    val discardChanges = handle {
        temp
    }

    fun saveCurrentModules() {
        temp = this.current
    }
}
