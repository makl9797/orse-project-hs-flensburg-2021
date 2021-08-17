package stores

import dev.fritz2.binding.RootStore
import kotlinx.browser.document
import models.store.Module
import models.store.ModuleSettings
import modules.moduleCatalog

val moduleStore = ModuleStore(
    listOf(
        moduleCatalog.calendar.createModule(
            ModuleSettings(60, 20, 1, 1)
        ),
        moduleCatalog.table.createModule(
            ModuleSettings(60, 20, 1, 23)
        )

    )
)


class ModuleStore(initModules: List<Module>) : RootStore<List<Module>>(initModules) {
    var temp: List<Module> = listOf()

    val addModule = handle { model, newModule: Module ->
        model + moduleCatalog.newModule(newModule.type, newModule.settings, newModule.card)
    }

    val removeModule = handle { model, moduleId: String ->
        console.log(moduleId)
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
        temp = moduleStore.current
    }
}
