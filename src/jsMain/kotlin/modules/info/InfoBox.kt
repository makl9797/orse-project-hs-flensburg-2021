package modules.info

import dev.fritz2.components.box
import dev.fritz2.components.clickButton
import dev.fritz2.components.inputField
import dev.fritz2.components.modal
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.values
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDate
import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings
import modules.info.selectCustomerModal.selectCustomerModal
import stores.*
import stores.BookingStore.endTimeStore
import stores.BookingStore.priceStore
import stores.BookingStore.startTimeStore

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
            // styling omitted for readability
            box {
                +("Subject Name: Test" + subject?.name)
            }
            box {
                +("Type: " + subject?.type)
            }
            box {
                +("Day: " + SelectedDayStore.current?.day)
            }
            box {
                +("Number of days: ")
                inputField {
                    type("number")
                    placeholder("42")
                    step("1")
                    events {
                        changes.values() handledBy endTimeStore.handle { _, new ->
                            daysToEndDate(
                                startDate = startTimeStore.current.toLocalDate(),
                                daysUntilEnd = new.toInt()
                            )
                        }
                    }
                }
            }
            box {
                +"Price: 5.0 Euro"
                inputField {
                    type("number")
                    placeholder("Euro")
                    events {
                        changes.values() handledBy priceStore.handle { _, new ->
                            new.toDouble()
                        }
                    }
                }
            }
            box {
                clickButton {
                    text("Kunden auswählen")
                    type { success }
                } handledBy modal { close ->
                    content {
                        selectCustomerModal("CustomerModal", close)
                    }
                }
            }
            SelectedCustomerStore.data.render { customer ->
                box {
                    if (customer != null) {
                        +("Kunde: " + customer.firstname + " " + customer.lastname)
                    }
                }
            }
            box {
                clickButton {
                    text("Buchen")
                    type { success }
                }.events.map {
                    BookingStore.current
                } handledBy BookingListStore.save

            }


        }
    }
}

fun daysToEndDate(startDate: LocalDate, daysUntilEnd: Int): String {
    return startDate.plus(DatePeriod(days = daysUntilEnd)).toString()
}



