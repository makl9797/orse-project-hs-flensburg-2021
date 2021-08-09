package components.content

import dev.fritz2.components.clickButton
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.moduleWrapper(
    style: Style<BoxParams>,
    id: String,
    title: String,
    content: RenderContext.(style: Style<BoxParams>) -> Unit
) {
    div({
        width { maxContent }
        height { maxContent }
        border {
            width { "0.1rem" }
            style { solid }
            color { primary.main }
        }
    }, id = id) {
        div({
            height { "1.4rem" }
            display { flex }
            justifyContent { spaceBetween }
            alignItems { center }
            paddings { left { small } }
            borders {
                bottom {
                    width { "0.1rem" }
                    style { solid }
                    color { primary.main }
                }
            }
        }, id = id + "Title") {
            +title
            clickButton({
            }) {
                icon { close }
                type { danger }
                size { small }
                variant { ghost }
            }
        }
        div({
            style()
            minWidth { "15rem" }
            minHeight { "15rem" }
            width { "15rem" }
            height { "15rem" }
        }) {
            content {
                width { "inherit" }
                height { "inherit" }
            }
        }
    }

}