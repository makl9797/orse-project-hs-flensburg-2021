package components.content

import dev.fritz2.binding.SubStore
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import kotlinx.browser.document
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.store.AppState
import models.store.L
import models.store.Module
import models.store.ModuleSettings

@ExperimentalCoroutinesApi
fun RenderContext.moduleWrapper(
    style: Style<BoxParams>,
    id: String,
    mode: AppState.Mode,
    subStore: SubStore<List<Module>, List<Module>, Module>,
    content: RenderContext.(style: Style<BoxParams>) -> Unit
): Div {
    val settingsStore = subStore.sub(L.Module.settings)
    val updateModuleSettings = settingsStore.handle { _, action: ModuleSettings ->
        action
    }

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
                top { "${settingsStore.current.parentY}px" }
                left { "${settingsStore.current.parentX}px" }
            }
        }
    }, id = "${id}Module") {
        moduleTitleBar("${id}Module", subStore.current.card.moduleName, mode)
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
            width { "${settingsStore.current.width}px" }
            height { "${settingsStore.current.height}px" }
            style()
        }, id = id) {
            content {
                width { "inherit" }
                height { "inherit" }
            }
            when (mode) {
                AppState.Mode.WORK -> {

                }
                AppState.Mode.EDIT -> {
                    mouseups.events.map {
                        val moduleElement = document.getElementById(id)
                        ModuleSettings(
                            moduleElement?.clientWidth!!.toDouble(),
                            moduleElement.clientHeight.toDouble(),
                        )
                    } handledBy updateModuleSettings
                }
                else -> {

                }
            }

        }
    }
}