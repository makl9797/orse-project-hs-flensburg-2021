package components.navigation

import components.navigation.moduleCollection.moduleCollectionMain
import dev.fritz2.components.clickButton
import dev.fritz2.components.modal
import dev.fritz2.components.pushButton
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.store.AppState.Mode
import stores.appStateStore

@ExperimentalCoroutinesApi
fun RenderContext.navigationEditMenu(id: String) {
    div(id = id) {
        clickButton({
            margins { horizontal { giant } }
            width { "10rem" }
        }, id = "moduleOverviewButton") {
            text("Module")
        } handledBy modal(id = "moduleOverviewModal") { close ->
            placement { center }
            width { small }
            hasCloseButton(false)
            content { moduleCollectionMain("moduleOverview", close) }
        }

        clickButton({
            margins { horizontal { tiny } }
        }, id = "saveSettingsButton") {
            text("Speichern")
            type { success }
        }.events.map { "Test" } handledBy appStateStore.save

        pushButton({
            margins { horizontal { tiny } }
        }, id = "abortSettingsButton") {
            text("Verwerfen")
            type { danger }
            events {
                clicks.events.map { Mode.WORK } handledBy appStateStore.changeMode
            }
        }
    }
}