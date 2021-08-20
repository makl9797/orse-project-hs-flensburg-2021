package components.content

import dev.fritz2.binding.SubStore
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.FlexParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.app.AppState
import models.app.L
import models.app.Module

@ExperimentalCoroutinesApi
fun RenderContext.moduleWrapper(
    style: Style<BoxParams>,
    id: String,
    mode: AppState.Mode,
    subStore: SubStore<List<Module>, List<Module>, Module>,
    content: RenderContext.(style: Style<FlexParams>) -> Unit
): Div {
    val settingsStore = subStore.sub(L.Module.settings)
    return div({
        border {
            width { "0.1rem" }
            style { solid }
            color { primary.mainContrast }
        }
        grid {
            column {
                start {
                    settingsStore.current.startX.toString()
                }
                end { (settingsStore.current.startX + settingsStore.current.width - 1).toString() }
            }
            row {
                start {
                    settingsStore.current.startY.toString()
                }
                end {
                    (settingsStore.current.startY + settingsStore.current.height - 1).toString()
                }
            }
        }
    }, id = "${id}Module") {
        moduleTitleBar("${id}Module", subStore.current.card.moduleName, mode, settingsStore)
        div({
            overflow { auto }
            width { "auto" }
            height { "${settingsStore.current.height - 1.6}rem" }
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
                }
                else -> {

                }
            }

        }
    }
}