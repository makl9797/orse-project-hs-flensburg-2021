package stores.app

import dev.fritz2.binding.RootStore
import models.store.AppState
import models.store.AppState.Mode

object AppStateStore : RootStore<AppState>(AppState(Mode.WORK)) {
    val save = handleAndEmit<String, Mode> { model, layout ->
        emit(Mode.WORK)
        model
    }

    val changeMode = handleAndEmit<Mode, Mode> { _, newMode: Mode ->
        emit(newMode)
        AppState(newMode)
    }

    init {
        changeMode handledBy ModuleStore.saveCurrentModules
    }
}