package stores.data

import dev.fritz2.binding.RootStore
import models.data.Customer

/**
 * SelectedCustomerStore
 *
 * Contains the current selected customer in the infobox module
 */
object SelectedCustomerStore : RootStore<Customer?>(null) {
    init {
        syncBy(BookingStore.updateCustomer)
    }
}
