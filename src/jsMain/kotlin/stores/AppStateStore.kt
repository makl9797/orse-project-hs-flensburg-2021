package stores

import dev.fritz2.binding.RootStore
import stores.models.AppState

class AppStateStore(init: AppState) : RootStore<AppState>(init) {

}