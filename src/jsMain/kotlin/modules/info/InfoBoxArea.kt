package modules.info

import dev.fritz2.components.stackUp
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.h3
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * InfoBoxArea
 * Component-Container for every section in the infobox
 */
@ExperimentalCoroutinesApi
fun RenderContext.infoBoxArea(id: String, title: String, content: RenderContext.() -> Unit) {
    stackUp({
        padding { "1rem" }
        margin { "1rem" }
        border {
            width { thin }
            style { solid }
        }
        radius { "1rem" }
        width { "-webkit-fill-available" }
    }, id = id) {
        items {
            h3({ textDecoration { underline } }) { +title }
            content()
        }
    }
}