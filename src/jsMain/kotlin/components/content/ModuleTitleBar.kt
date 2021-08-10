package components.content

import dev.fritz2.components.clickButton
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.AppState

@ExperimentalCoroutinesApi
fun RenderContext.moduleTitleBar(
    id: String,
    title: String,
    mode: AppState.Mode,
) {
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
        when (mode) {
            AppState.Mode.WORK -> {

            }
            AppState.Mode.EDIT -> {
                clickButton({
                }) {
                    icon { close }
                    type { danger }
                    size { small }
                    variant { ghost }
                }
            }
            else -> {
            }
        }
    }
}