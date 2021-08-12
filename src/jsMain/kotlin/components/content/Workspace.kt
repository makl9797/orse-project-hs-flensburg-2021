package components.content

import dev.fritz2.components.gridBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.AppState

@ExperimentalCoroutinesApi
fun RenderContext.workspace(id: String, mode: AppState.Mode) {
    gridBox({
        columns { repeat(autoFill) { minmax("1rem", "1.5rem") } }
        rows { repeat(autoFill) { minmax("1rem", "1.5rem") } }
        height { "100%" }
    }, id = id) {
        moduleContainer(mode)
    }
}