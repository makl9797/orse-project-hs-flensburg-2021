package stores

import dev.fritz2.binding.RootStore
import models.store.ModuleCard
import models.store.ModuleProperties
import models.store.ModuleSettings

class ModuleStore(initCard: ModuleCard, initSettings: ModuleSettings) :
    RootStore<ModuleProperties>(ModuleProperties(initCard, initSettings)) {

}
