package packages

import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.module(id: String, width: Int, height: Int, content: Div.() -> Unit): Div {

    return div({
        width { "${width}vh" }
        height { "${height}vh" }
        padding { normal }
        border {
            width { thin }
            color { secondary.main }
        }
        overflow { auto }
        css("resize: both")
    }, id = id) {
        content()
    }
}