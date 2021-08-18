package modules.info

import dev.fritz2.components.*
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.identification.uniqueId
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import dev.fritz2.styling.theme.ColorScheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.Booking
import models.Customer
import models.Subject
import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings
import modules.info.selectCustomerModal.CustomersModal
import stores.*

class InfoBox {
    private var count = -1

    private val type = Module.Type.INFOBOX

    private val defaultSettings = ModuleSettings(
        width = 60,
        height = 40,
        startX = 1,
        startY = 1
    )
    private val card = ModuleCard(
        moduleName = "Informationen",
        moduleDescription = "Dieses Modul stellt Daten in einem größeren Fenster dar. Außerdem können noch zusätzliche Informationen dargestellt werden.",
        exampleImageSrc = "https://via.placeholder.com/150?text=Module+Example+PicPlaceholder"
    )


    fun createModule(settings: ModuleSettings = defaultSettings, card: ModuleCard = this.card): Module {
        count++
        val id = "infoBox${count}"
        return Module(id, type, settings, card)
    }
}

@ExperimentalCoroutinesApi
fun RenderContext.infoBox(id: String, style: Style<BoxParams>) {

    div({
        style()
        background { color { "grey" } }
    }, id = id) {
        SelectedSubjectStore.data.render { subject ->
            stackUp {
                items {
                    // styling omitted for readability
                    box {
                        +("Subject Name: " + subject?.name)
                    }
                    box {
                        +("Type: " + subject?.type)
                    }
                    box {
                        +("Day: " + dayStore.current?.day)
                    }
                    box {
                        +("Number of days: ")
                        inputField {
                            type("number")
                            placeholder("42")
                            step("1")
                        }
                    }
                    box {
                        +"Price: 5.0 Euro"
                        inputField {
                            type("number")
                            placeholder("Euro")
                        }
                    }
                    box {
                        clickButton {
                            text("Kunden auswählen")
                            type { ColorScheme("#00A848", "#2D3748", "#E14F2A", "#2D3748") }
                        } handledBy modal { close ->
                            content {
                                CustomersModal("CustomerModal", close)
                            }
                        }
                    }
                    SelectedCustomerStore.data.render { customer ->
                        box {
                            if (customer != null) {
                                +("Kunde: " + customer.firstname+ " " +customer.lastname)
                            }
                        }
                    }
                    box {
                        clickButton {
                            text("Buchen")
                            type { ColorScheme("#00A848", "#2D3748", "#E14F2A", "#2D3748") }
                        }.events.map {
                        }

                    }
                }
            }
        }
    }

}



