package components

import components.content.workspace
import components.navigation.navigation
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stores.appStateStore

@ExperimentalCoroutinesApi
fun RenderContext.app() {
    appStateStore.data.render { state ->
        navigation("navigation", state.mode)
        workspace("workspace", state.mode)
    }

}

