package components

import components.content.workspace
import components.navigation.navigation
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import modules.moduleCatalog
import stores.appStateStore

@ExperimentalCoroutinesApi
fun RenderContext.app() {
    appStateStore.data.render { state ->
        navigation("navigation", state.mode, moduleCatalog)
        workspace("workspace", state.mode)
    }

}
