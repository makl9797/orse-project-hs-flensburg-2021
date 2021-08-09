package components.content

import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.workspace(id: String) {
    div(id = id) {
        moduleContainer("moduleContainer")
    }
}