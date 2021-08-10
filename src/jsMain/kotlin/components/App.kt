package components

import components.content.workspace
import components.navigation.navigation
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.AppState

@ExperimentalCoroutinesApi
fun RenderContext.app() {
    navigation("navigation", AppState.Mode.EDIT)
    workspace("workspace", AppState.Mode.EDIT)
}

