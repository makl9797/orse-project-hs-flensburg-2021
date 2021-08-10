package components.navigation

import components.navigation.appSettings.appSettingsMain
import dev.fritz2.components.dropdown
import dev.fritz2.components.menu
import dev.fritz2.components.modal
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.store.AppState.Mode
import stores.appStateStore

@ExperimentalCoroutinesApi
fun RenderContext.navigationMenu(id: String) {
    dropdown(id = "navigationDrawer") {
        placement { bottom }
        alignment { end }
        content {
            menu(id = id) {
                header("Menü")
                entry {
                    icon { grid }
                    text("Module anpassen")
                    events {
                        clicks.events.map { Mode.EDIT } handledBy appStateStore.changeMode
                    }
                }
                divider()
                entry {
                    icon { settings }
                    text("Einstellungen")
                    events {
                        clicks handledBy modal(id = "appSettingsOverviewModal") { close ->
                            placement { center }
                            width { small }
                            hasCloseButton(false)
                            content { appSettingsMain("appSettingsOverview", close) }
                        }
                    }
                }
            }
        }
    }
}