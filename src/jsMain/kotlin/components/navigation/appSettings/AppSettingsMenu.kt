package components.navigation.appSettings

import dev.fritz2.components.menu
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.appSettingsMenu(id: String) {
    menu({
        paddings { right { "1rem" } }
        borders { right { width { "0.1rem" } } }
    }, id = id) {
        header("Layout")
        entry {
            icon { edit }
            text("Theme")
        }
        divider()
        header("Server")
        entry {
            icon { archive }
            text("Datenbank")
        }
    }
}