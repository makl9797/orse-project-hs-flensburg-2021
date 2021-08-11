package stores

import dev.fritz2.binding.RootStore
import models.store.Module
import models.store.ModuleSettings

val moduleStore = ModuleStore(listOf())


class ModuleStore(initModules: List<Module>) : RootStore<List<Module>>(initModules) {
    val saveChanges = handle { model, modules: ModuleSettings ->
        console.log(modules)
        model
    }

    val addModule = handle { model, newModule: Module ->
        model + newModule
    }
}
