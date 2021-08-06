package components

import dev.fritz2.binding.RootStore
import dev.fritz2.binding.SimpleHandler
import dev.fritz2.components.box
import dev.fritz2.components.gridBox
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.OverflowXValues
import kotlinx.coroutines.ExperimentalCoroutinesApi
import model.AppState
import modules.wrapper.moduleContainer

class AppStateStore(initialAppStateStore: AppState) : RootStore<AppState>(initialAppStateStore) {
    val switchMode: SimpleHandler<Unit> = handle { model ->
        AppState(!model.isEditable)
    }
}

val appStore = AppStateStore(AppState())

@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.app(): Div {
    return div({
        height { "100vh" }
    }, id = "app") {
        appStore.data.render { state ->
            gridBox({
                rows { "72px auto" }
            }) {
                box({
                    grid {
                        row {
                            start { "1" }

                        }
                    }
                }) {
                    navbar(state.isEditable)
                }
                moduleContainer({
                    wrap { wrap }
                    grid {
                        row {
                            start { "2" }
                        }
                    }
                    overflowX { OverflowXValues.hidden }
                }, "modules")

            }
        }
    }
}



