package components.content

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.AppState

@ExperimentalCoroutinesApi
fun RenderContext.moduleWrapper(
    style: Style<BoxParams>,
    id: String,
    title: String,
    mode: AppState.Mode,
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
        moduleTitleBar(id, title, mode)
        div({
            overflow { auto }
            when (mode) {
                AppState.Mode.WORK -> {

                }
                AppState.Mode.EDIT -> {
                    css("resize: both")
                }
                else -> {

                }
            }
            minWidth { "15rem" }
            minHeight { "15rem" }
            width { "15rem" }
            height { "15rem" }
            style()
        }) {
            content {
                width { "inherit" }
                height { "inherit" }
            }
        }
    }

}