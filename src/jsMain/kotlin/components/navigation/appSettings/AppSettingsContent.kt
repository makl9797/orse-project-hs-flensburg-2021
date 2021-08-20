package components.navigation.appSettings

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.h1
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.appSettingsContent(id: String) {
    div(id = id) {
        h1({ color { gray200 } }) {
            +"Not available yet"
        }
    }

}