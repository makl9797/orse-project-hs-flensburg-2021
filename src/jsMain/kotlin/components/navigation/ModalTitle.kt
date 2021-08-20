package components.navigation

import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.modalTitle(id: String, title: String) {
    flexBox({
        alignContent { start }
        justifyContent { flexStart }
    }, id = id + "Title") {
        h1 { +title }
    }
}