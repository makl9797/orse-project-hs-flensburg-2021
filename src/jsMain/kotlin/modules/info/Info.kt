package modules.info

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.infoModule(id: String, style: Style<BoxParams>) {
    div({
        style()
        background { color { primary.main } }
    }, id = id) {
    }
}