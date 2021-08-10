package components.content

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.AppState

@ExperimentalCoroutinesApi
fun RenderContext.moduleContainer(id: String, mode: AppState.Mode) {
    moduleWrapper({

    }, id = "exampleBoxModule", "Beispielmodul", mode) { style ->
        div(style, id = "exampleBox") {
        }
    }
}