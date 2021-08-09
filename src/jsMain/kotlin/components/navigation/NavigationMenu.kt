package components.navigation

import dev.fritz2.components.dropdown
import dev.fritz2.components.menu
import dev.fritz2.components.modal
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.navigationMenu() {
    dropdown {
        content {
            menu {
                header("Menü")
                entry {
                    icon { grid }
                    text("Module anpassen")
                }
                entry {
                    icon { settings }
                    text("Einstellungen")
                    events {
                        clicks handledBy modal { content { appSettingsOverview() } }
                    }
                }
            }
        }
    }
}