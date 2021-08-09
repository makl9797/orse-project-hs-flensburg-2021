package components.content

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.moduleWrapper(style: Style<BoxParams>, id: String, content: RenderContext.() -> Unit) {
    div(style, id = id) {
        content()
    }

}