package stores

import dev.fritz2.binding.RootStore
import models.store.AppState

class AppStateStore(init: AppState) : RootStore<AppState>(init) {

}