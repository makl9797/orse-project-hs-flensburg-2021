package components.navigation

import dev.fritz2.components.clickButton
import dev.fritz2.components.modal
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.navigationEditMenu(id: String) {
    div(id = id) {
        clickButton({
            margins { horizontal { giant } }
        }, id = "moduleOverviewButton") {
            text("Modul-Übersicht")
        } handledBy modal(id = "moduleOverviewModal") { content { moduleOverview("moduleOverview") } }

        clickButton({
            margins { horizontal { tiny } }
        }, id = "saveSettingsButton") {
            text("Speichern")
            type { success }
        }

        clickButton({
            margins { horizontal { tiny } }
        }, id = "abortSettingsButton") {
            text("Verwerfen")
            type { danger }
        }
    }
}