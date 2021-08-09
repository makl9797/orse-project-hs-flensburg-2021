package components.navigation


import dev.fritz2.binding.SimpleHandler
import dev.fritz2.components.clickButton
import dev.fritz2.components.menu
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.appSettingsOverview(id: String, modalClose: SimpleHandler<Unit>) {
    div(id = id) {
        div({
            display { flex }
            alignContent { start }
            justifyContent { flexStart }
        }, id = "appSettingsOverviewHeader") {
            h1 { +"Einstellungen" }
        }

        div({
            display { flex }
            paddings { top { "1rem" } }
        }, id = "appSettingsOverviewBody") {
            menu({
                paddings { right { "1rem" } }
                borders { right { width { "0.1rem" } } }
            }) {
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
            div(id = "appSettingsOverviewContent") {

            }
        }

        div({
            display { flex }
            justifyContent { flexEnd }
        }, id = "appSettingsOverviewFooter") {
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
}