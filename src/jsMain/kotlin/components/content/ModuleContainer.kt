package components.content

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.moduleContainer(id: String) {
    moduleWrapper({
        overflow { auto }
        css("resize: both")
    }, id = "exampleBoxModule", "Beispielmodul") { style ->
        div(style, id = "exampleBox") {
        }
    }
}