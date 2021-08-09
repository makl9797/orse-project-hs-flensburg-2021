package components.navigation

import dev.fritz2.components.clickButton
import dev.fritz2.components.modal
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.navigationEditMenu() {
    clickButton({
        margins { horizontal { giant } }
    }) {
        text("Modul-Übersicht")
    } handledBy modal { content { moduleOverview() } }

    clickButton({
        margins { horizontal { tiny } }
    }) {
        text("Speichern")
        type { success }
    }

    clickButton({
        margins { horizontal { tiny } }
    }) {
        text("Verwerfen")
        type { danger }
    }
}