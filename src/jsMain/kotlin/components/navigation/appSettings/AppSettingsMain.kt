package components.navigation.appSettings


import components.navigation.modalTitle
import dev.fritz2.binding.SimpleHandler
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.appSettingsMain(id: String, modalClose: SimpleHandler<Unit>) {
    div(id = id) {
        modalTitle("appSettingsTitle", "Einstellungen")

        flexBox({
            paddings { vertical { "1rem" } }
        }, id = "appSettingsOverviewBody") {
            appSettingsMenu("appSettingsMenu")
            appSettingsContent("appSettingsContent")
        }

        appSettingsFooter("appSettingsFooter", modalClose)
    }
}