package components.navigation.appSettings

import dev.fritz2.binding.SimpleHandler
import dev.fritz2.components.clickButton
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.appSettingsFooter(id: String, modalClose: SimpleHandler<Unit>) {

    flexBox({
        justifyContent { flexEnd }
    }, id = id) {
        clickButton({
            margins { right { tiny } }
        }) {
            type { success }
            variant { outline }
            text("Speichern")
        } handledBy modalClose

        clickButton({
            margins { left { tiny } }
        }) {
            type { danger }
            variant { outline }
            text("Verwerfen")
        } handledBy modalClose
    }

}