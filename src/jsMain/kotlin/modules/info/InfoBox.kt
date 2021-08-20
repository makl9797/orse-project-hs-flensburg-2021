package modules.info

import dev.fritz2.components.*
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.values
import dev.fritz2.styling.params.FlexParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDate
import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings
import modules.info.selectCustomerModal.selectCustomerModal
import stores.data.*
import stores.data.BookingStore.endTimeStore
import stores.data.BookingStore.priceStore
import stores.data.BookingStore.startTimeStore

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
fun RenderContext.infoBox(id: String, style: Style<FlexParams>) {

    SelectedSubjectStore.data.combine(SelectedDayStore.data.combine(SelectedCustomerStore.data) { day, customer ->
        object {
            val day = day
            val customer = customer
        }
    }) { subject, dayAndCustomer ->
        object {
            val day = dayAndCustomer.day
            val customer = dayAndCustomer.customer
            val subject = subject
        }
    }.render { selectedData ->
        lineUp({
            style()
            alignItems { stretch }
        }, id = id) {
            items {
                infoBoxArea("subjectBox", "Objekt") {
                    lineUp({
                        alignItems { center }
                    }) {
                        items {
                            h4 {
                                +"Objektname: "
                            }
                            a {
                                +(selectedData.subject?.name ?: "Kein Objekt ausgewählt")
                            }
                        }
                    }
                    lineUp({
                        alignItems { center }
                    }) {
                        items {
                            h4 {
                                +"Objektbeschreibung: "
                            }
                            a {
                                +(selectedData.subject?.description ?: "Kein Objekt ausgewählt")
                            }
                        }
                    }
                    lineUp({
                        alignItems { center }
                    }) {
                        items {
                            h4 {
                                +"Objekttyp: "
                            }
                            a {
                                +(selectedData.subject?.type ?: "Kein Objekt ausgewählt")
                            }
                        }
                    }
                }
                infoBoxArea("customerBox", "Kunde") {
                    if (selectedData.customer != null) {
                        lineUp({
                            alignItems { center }
                        }) {
                            items {
                                h4 {
                                    +"Name: "
                                }
                                a {
                                    +"${selectedData.customer.firstname} ${selectedData.customer.lastname}"
                                }
                            }
                        }
                        stackUp({

                        }) {
                            items {
                                h4 {
                                    +"Adresse: "
                                }
                                a {
                                    +selectedData.customer.address.street
                                }
                                a {
                                    +"${selectedData.customer.address.zip} ${selectedData.customer.address.city}"
                                }

                            }
                        }
                    }
                    box {
                        clickButton {
                            text("Kunde auswählen")
                            type { success }
                        } handledBy modal { close ->
                            hasCloseButton(false)
                            placement { center }
                            content {
                                selectCustomerModal("CustomerModal", close)
                            }
                        }
                    }
                }
                infoBoxArea("bookingBox", "Buchen") {
                    lineUp({
                        alignItems { center }
                    }) {
                        items {
                            h4 {
                                +"Ausgewählter Tag: "
                            }
                            a {
                                +(selectedData.day?.day ?: "Kein Tag ausgewählt")
                            }
                        }
                    }
                    lineUp({
                        alignItems { center }
                    }) {
                        items {
                            h4 {
                                +"Tage buchen: "
                            }
                            inputField {
                                value("0")
                                type("number")
                                placeholder("3")
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
                    }
                    lineUp({
                        alignItems { center }
                    }) {
                        items {
                            h4 {
                                +"Buchung bis einschließlich: "
                            }
                            endTimeStore.data.render { endTime ->
                                a {
                                    +endTime
                                }
                            }
                        }
                    }
                    lineUp({
                        alignItems { center }
                    }) {
                        items {
                            h4 {
                                +"Preis in Euro: "
                            }
                            inputField {
                                value(priceStore.data.map { it.toString() })
                                type("number")
                                placeholder("Euro")
                                events {
                                    changes.values() handledBy priceStore.handle { _, new ->
                                        new.toDouble()
                                    }
                                }
                            }
                        }
                    }
                    BookingStore.data.render { booking ->
                        clickButton {
                            text("Buchen")
                            if (booking._id == "empty"
                                || booking.customer._id == "empty"
                                || booking.subject._id == "empty"
                                || booking.endTime == ""
                                || booking.price == 0.0
                            ) {
                                disabled(true)
                            } else {
                                disabled(false)
                            }
                            type { success }
                        }.events.map {
                            BookingStore.current
                        } handledBy BookingListStore.save
                    }
                }
            }
        }
    }
}


fun daysToEndDate(startDate: LocalDate, daysUntilEnd: Int): String {
    return startDate.plus(DatePeriod(days = daysUntilEnd - 1)).toString()
}



