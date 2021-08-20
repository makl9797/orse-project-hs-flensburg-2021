package components.navigation

import components.navigation.appSettings.appSettingsMain
import components.navigation.customerModal.addCustomerModal
import components.navigation.subjectModal.addSubjectModal
import dev.fritz2.components.clickButton
import dev.fritz2.components.dropdown
import dev.fritz2.components.menu
import dev.fritz2.components.modal
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.app.AppState.Mode
import stores.app.AppStateStore

@ExperimentalCoroutinesApi
fun RenderContext.navigationMenu(id: String) {

    div {
        clickButton({
            margins { horizontal { giant } }
            width { "12rem" }
        }, id = "addCustomerButton") {
            text("Kunde hinzufügen")
        } handledBy modal(id = "addCustomerModal") { close ->
            placement { center }
            width { small }
            hasCloseButton(false)
            content { addCustomerModal("addCustomerModalContent", close) }
        }
        clickButton({
            margins { horizontal { giant } }
            width { "12rem" }
        }, id = "addSubjectButton") {
            text("Objekt hinzufügen")
        } handledBy modal(id = "addSubjectModal") { close ->
            placement { center }
            width { small }
            hasCloseButton(false)
            content {
                addSubjectModal("addSubjectModalContent", close)
            }
        }
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
                            clicks.events.map { Mode.EDIT } handledBy AppStateStore.changeMode
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
}