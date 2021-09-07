package modules.info.selectCustomerModal

import dev.fritz2.binding.SimpleHandler
import dev.fritz2.components.clickButton
import dev.fritz2.components.dataTable
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.data.Customer
import models.data.L
import stores.data.CustomerListStore
import stores.data.SelectedCustomerStore
/**
 * this function is used to render the selection of a customer from the customers list
 */
@ExperimentalCoroutinesApi
fun RenderContext.selectCustomerModal(id: String, close: SimpleHandler<Unit>) {

    CustomerListStore.data.render {

        div({
            background { color { "orange" } }
        }, id = id) {
            dataTable(
                rows = CustomerListStore,
                rowIdProvider = Customer::firstname,
                selection = SelectedCustomerStore

            ) {
                columns {
                    column(title = "Firstname") { lens(L.Customer.firstname) }
                    column(title = "Lastname") { lens(L.Customer.lastname) }
                }
            }
        }

        flexBox({
            wrap { wrap }
            justifyContent { flexEnd }
        }, id = id + "Form") {
            clickButton({
                margins {
                    top { small }
                    right { small }
                }
            }) {
                variant { outline }
                type { success }
                text("Auswählen")
            } handledBy close
            clickButton({
                margins { top { small } }
            }) {
                text("Schließen")
            } handledBy close
        }
    }
}