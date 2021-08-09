package components.navigation

import dev.fritz2.components.dropdown
import dev.fritz2.components.menu
import dev.fritz2.components.modal
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

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
                            content { appSettingsOverview("appSettingsOverview", close) }
                        }
                    }
                }
            }
        }
    }
}