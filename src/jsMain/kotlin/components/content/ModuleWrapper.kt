package components.content

import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.store.AppState
import models.store.ModuleSettings
import stores.moduleStore

@ExperimentalCoroutinesApi
fun RenderContext.moduleWrapper(
    style: Style<BoxParams>,
    id: String,
    title: String,
    settings: ModuleSettings,
    mode: AppState.Mode,
    content: RenderContext.(style: Style<BoxParams>) -> Unit
): Div {
    return div({
        width { maxContent }
        height { maxContent }
        border {
            width { "0.1rem" }
            style { solid }
            color { primary.main }
        }
        position {
            relative {
                top { "${settings.parentY}px" }
                left { "${settings.parentX}px" }
            }
        }
    }, id = "${id}Module") {
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
            width { "${settings.width}px" }
            height { "${settings.height}px" }
            style()
        }) {
            content {
                width { "inherit" }
                height { "inherit" }
            }
            when (mode) {
                AppState.Mode.WORK -> {

                }
                AppState.Mode.EDIT -> {
                    mouseups.events.map { settings } handledBy moduleStore.saveChanges
                }
                else -> {

                }
            }

        }
    }
}