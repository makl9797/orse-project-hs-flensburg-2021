package stores

import dev.fritz2.binding.RootStore
import models.store.AppState
import models.store.AppState.Mode

object AppStateStore : RootStore<AppState>(AppState(Mode.WORK)) {
    val save = handleAndEmit<String, Mode> { model, layout ->
        emit(Mode.WORK)
        model
    }

    val changeMode = handle { _, newMode: Mode ->
        if (newMode == Mode.EDIT) {
            ModuleStore.saveCurrentModules()
        }
        AppState(newMode)
    }
}