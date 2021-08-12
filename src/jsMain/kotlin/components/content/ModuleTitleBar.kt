package components.content

import dev.fritz2.components.clickButton
import dev.fritz2.components.popover
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.store.AppState
import stores.moduleStore

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
                div {
                    popover({
                        width { "auto" }
                    }) {
                        toggle {
                            clickButton {
                                icon { expand }
                                size { small }
                                variant { ghost }
                            }

                        }
                        placement { bottom }
                        hasCloseButton(false)
                        closeOnBlur(false)
                        content {
                            moduleMoveController(id)
                        }
                    }
                    clickButton {
                        icon { close }
                        type { danger }
                        size { small }
                        variant { ghost }
                    }.events.map { id } handledBy moduleStore.removeModule
                }
            }
            else -> {
            }
        }
        mousedowns.events handledBy moveModuleStore.onMouseDown
    }
}