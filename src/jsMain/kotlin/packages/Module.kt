package packages

import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.module(id: String, content: Div.() -> Unit): Div {

    return div({
        minWidth { "max-content" }
        minHeight { "20vh" }
        border {
            width { thin }
            color { secondary.main }
        }
        flex {
            grow { "1" }
        }
        overflowY { auto }
        overflowX { hidden }
        css("resize: both")
    }, id = id) {
        content()
    }
}