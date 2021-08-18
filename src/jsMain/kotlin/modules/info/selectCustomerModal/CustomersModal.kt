package modules.info.selectCustomerModal

import dev.fritz2.binding.SimpleHandler
import dev.fritz2.binding.storeOf
import dev.fritz2.components.*
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.values
import dev.fritz2.identification.uniqueId
import dev.fritz2.styling.div
import dev.fritz2.styling.params.JustifyContentValues
import dev.fritz2.styling.params.WrapValues
import dev.fritz2.styling.style
import dev.fritz2.styling.theme.Theme.Companion.data
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.Address
import models.Customer
import models.L
import models.Subject
import models.store.L.Data.data
import stores.*

@ExperimentalCoroutinesApi
fun RenderContext.CustomersModal(id: String, close: SimpleHandler<Unit>) {


        div({
            background { color { "orange" } }
        }, id = id) {
            dataTable(
                rows = customersStore,
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
            margins { top { small } }
        }) {
            variant { outline }
            type { success }
            text("Auswählen")
        }handledBy close

    }

}