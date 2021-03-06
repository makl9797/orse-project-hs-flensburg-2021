package components.navigation.customerModal

import dev.fritz2.binding.storeOf
import dev.fritz2.components.clickButton
import dev.fritz2.components.flexBox
import dev.fritz2.components.formControl
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.values
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.data.Address
import models.data.Customer
import models.data.L
import stores.data.CustomerListStore


@ExperimentalCoroutinesApi
fun RenderContext.addCustomerForm(id: String) {
    val customerStore = storeOf(
        Customer(address = Address(street = "", city = "", zip = 0), firstname = "", lastname = "")
    )
    val fNameStore = customerStore.sub(L.Customer.firstname)
    val lNameStore = customerStore.sub(L.Customer.lastname)
    val addressStore = customerStore.sub(L.Customer.address)

    val streetStore = addressStore.sub(L.Address.street)
    val cityStore = addressStore.sub(L.Address.city)
    val zipStore = addressStore.sub(L.Address.zip)

    flexBox({
        wrap { wrap }
        justifyContent { flexEnd }
    }, id = id + "Form") {
        formControl {
            label("Vorname")
            inputField(value = fNameStore) {
                placeholder("Vorname")
            }
        }
        formControl({
            margins { top { small } }
        }) {
            label("Nachname")
            inputField(value = lNameStore) {
                placeholder("Nachname")
            }
        }
        formControl({
            margins { top { small } }
        }) {
            label("Adresse")
            inputField(value = streetStore) {
                placeholder("Straße & Hausnummer")
            }
        }
        formControl {
            inputField(value = cityStore) {
                placeholder("Ort/Stadt")
            }
        }
        formControl {
            inputField {
                value(zipStore.data.toString())
                placeholder("Postleitzahl")
                type("number")
                events {
                    changes.values() handledBy zipStore.handle { _, n: String ->
                        n.toInt()
                    }
                }
            }

        }
        customerStore.data.render { customer ->
            clickButton({
                margins { top { small } }
            }) {
                if (customer.firstname == "" || customer.lastname == "" || customer.address.city == "" || customer.address.street == "" || customer.address.zip == 0) {
                    disabled(true)
                } else {
                    disabled(false)
                }
                variant { outline }
                type { success }
                text("Hinzufügen")
            }.events.map {
                Customer(
                    address = customer.address,
                    firstname = customer.firstname,
                    lastname = customer.lastname
                )
            } handledBy CustomerListStore.save
        }
    }
}