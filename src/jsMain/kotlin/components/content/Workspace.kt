package components.content

import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.AppState

@ExperimentalCoroutinesApi
fun RenderContext.workspace(id: String, mode: AppState.Mode) {
    div(id = id) {
        moduleContainer("moduleContainer", mode)
    }
}