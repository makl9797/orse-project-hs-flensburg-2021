package stores

import dev.fritz2.binding.RootStore
import kotlinx.browser.document
import models.store.Module
import modules.moduleCatalog

val moduleStore = ModuleStore(listOf())


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
