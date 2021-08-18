package components.navigation.customerModal

import components.navigation.modalTitle
import dev.fritz2.binding.SimpleHandler
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.addCustomerModal(id: String, modalClose: SimpleHandler<Unit>) {
    div(id = id) {
        modalTitle(id, "Kunde hinzufügen")

        flexBox({
            paddings { vertical { "1rem" } }
        }, id = id + "Body") {
            addCustomerForm(id)
        }
        addCustomerFooter(id, modalClose)
    }
}