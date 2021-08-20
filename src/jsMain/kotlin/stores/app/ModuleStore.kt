package stores.app

import dev.fritz2.binding.RootStore
import kotlinx.browser.document
import models.app.AppState
import models.app.Module
import models.app.ModuleSettings
import modules.moduleCatalog

val moduleList =
    listOf(
        moduleCatalog.table.createModule(
            ModuleSettings(75, 30, 1, 1)
        ),
        moduleCatalog.calendar.createModule(
            ModuleSettings(26, 30, 75, 1)
        ),
        moduleCatalog.infoBox.createModule(
            ModuleSettings(100, 30, 1, 30)
        )

    )


object ModuleStore : RootStore<List<Module>>(moduleList) {
    var temp: List<Module> = listOf()

    val updateModule = handle { model, module: Module ->
        model.map {
            if (it.id == module.id) {
                module
            } else {
                it
            }
        }.toList()
    }

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

    val saveCurrentModules = handle { current, mode: AppState.Mode ->
        if (mode == AppState.Mode.EDIT) {
            temp = current
        }
        current
    }
}
