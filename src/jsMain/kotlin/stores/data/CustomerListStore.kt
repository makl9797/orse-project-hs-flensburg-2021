package stores.data

import dev.fritz2.binding.RootStore
import dev.fritz2.components.alert
import dev.fritz2.components.toast
import dev.fritz2.identification.uniqueId
import dev.fritz2.repositories.rest.restQuery
import models.data.Customer
import models.data.CustomerResource

/**
 * CustomerListStore
 *
 * Communicates with the database via the endpoint /customers
 */

object CustomerListStore : RootStore<List<Customer>>(emptyList()) {
    private const val CUSTOMER_ENDPOINT = "/customers"
    private val repo = restQuery<Customer, String, Unit>(CustomerResource, CUSTOMER_ENDPOINT, uniqueId())

    val query = handle {
        repo.query(Unit)
    }

    val save = handleAndEmit<Customer, Unit> { customers, new: Customer ->
        val temp = repo.addOrUpdate(customers, new)
        emit(Unit)
        temp
    }
    val remove = handle { customers, id: String ->
        repo.delete(customers, id)
    }

    init {
        query()
        syncBy(query)
        save handledBy query
        save handledBy toast {
            placement { bottomRight }
            background { success.main }
            hasCloseButton(false)
            content {
                alert {
                    severity { success }
                    title("Neuer Kunde angelegt!")
                }
            }
        }
    }
}
