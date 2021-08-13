package components.content

import dev.fritz2.binding.SubStore
import dev.fritz2.components.clickButton
import dev.fritz2.components.popover
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.store.AppState
import models.store.Module
import models.store.ModuleSettings
import stores.moduleStore

@ExperimentalCoroutinesApi
fun RenderContext.moduleTitleBar(
    id: String,
    title: String,
    mode: AppState.Mode,
    settingsStore: SubStore<List<Module>, Module, ModuleSettings>
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
        background { color { gray50 } }
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
                        content {
                            moduleMoveController(id, settingsStore)
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