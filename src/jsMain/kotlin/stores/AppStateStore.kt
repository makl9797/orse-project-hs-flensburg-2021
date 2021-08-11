package stores

import dev.fritz2.binding.RootStore
import models.store.AppState
import models.store.AppState.Mode

val appStateStore = AppStateStore(AppState(Mode.WORK))

class AppStateStore(init: AppState) : RootStore<AppState>(init) {


    val save = handleAndEmit<String, Mode> { model, layout ->
        emit(Mode.WORK)
        console.log(layout)
        model
    }

    val changeMode = handle { _, newMode: Mode ->
        if (newMode == Mode.EDIT) {
            moduleStore.saveCurrentModules()
        }
        AppState(newMode)
    }


}