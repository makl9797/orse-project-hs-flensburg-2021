package stores

import dev.fritz2.binding.RootStore
import models.store.Module
import models.store.ModuleCard
import models.store.ModuleProperties
import models.store.ModuleSettings

val moduleStore = ModuleStore(
    listOf(
        Module(
            "infoBox", Module.Type.INFOBOX, ModuleProperties(
                ModuleCard(
                    "infoboxCard",
                    "Infobox",
                    "Beschreibung",
                    "https://via.placeholder.com/150?text=Module+Example+PicPlaceholder",
                    ModuleSettings(
                        150.0,
                        150.0,
                        10.0,
                        20.0
                    )
                ),
                ModuleSettings(
                    400.0,
                    150.0,
                    10.0,
                    50.0
                )
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
