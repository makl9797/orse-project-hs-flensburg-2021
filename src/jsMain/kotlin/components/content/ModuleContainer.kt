package components.content

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.moduleContainer(id: String) {
    moduleWrapper({
        width { "auto" }
        height { "auto" }
    }, id = "exampleBoxModule") {
        div({
            width { "10rem" }
            height { "10rem" }
            background { color { primary.main } }
        }, id = "exampleBox") {

        }
    }
}