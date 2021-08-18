package stores

import dev.fritz2.binding.RootStore
import models.Customer

object SelectedCustomerStore : RootStore<Customer?>(null) {}