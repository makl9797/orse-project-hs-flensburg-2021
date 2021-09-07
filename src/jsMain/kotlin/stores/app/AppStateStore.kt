package stores.app

import dev.fritz2.binding.RootStore
import models.app.AppState
import models.app.AppState.Mode

/**
 * AppStateStore
 *
 * Contains the current application state like the current mode
 */

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