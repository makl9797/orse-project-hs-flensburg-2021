package stores

import dev.fritz2.binding.RootStore
import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings

val moduleStore = ModuleStore(
    listOf(
        Module(
            id = "infoBox",
            type = Module.Type.INFOBOX,
            settings = ModuleSettings(
                400.0,
                150.0,
                10.0,
                50.0
            ),
            card = ModuleCard(
                "Infobox",
                "Beschreibung",
                "https://via.placeholder.com/150?text=Module+Example+PicPlaceholder",
            )
        )
    )
)


class ModuleStore(initModules: List<Module>) : RootStore<List<Module>>(initModules) {
    val saveChanges = handle { model, modules: ModuleSettings ->
        console.log(modules)
        model
    }
}
