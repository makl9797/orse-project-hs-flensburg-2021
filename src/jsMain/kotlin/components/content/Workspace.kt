package components.content

import dev.fritz2.components.gridBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.app.AppState

@ExperimentalCoroutinesApi
fun RenderContext.workspace(id: String, mode: AppState.Mode) {
    gridBox({
        columns { repeat(autoFill) { "1rem" } }
        rows { repeat(autoFill) { "1rem" } }
        height { "100%" }
    }, id = id) {
        moduleContainer(mode)
    }
}