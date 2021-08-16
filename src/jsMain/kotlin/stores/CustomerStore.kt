package stores

import dev.fritz2.binding.RootStore
import dev.fritz2.identification.uniqueId
import dev.fritz2.repositories.rest.restQuery
import models.Customer
import models.CustomerResource

val customersStore = CustomerStore(emptyList(), id = "customers")
const val CUSTOMER_ENDPOINT = "/customers"

class CustomerStore(init: List<Customer>, id: String) : RootStore<List<Customer>>(init, id = id) {
    private val repo = restQuery<Customer, String, Unit>(CustomerResource, CUSTOMER_ENDPOINT, uniqueId())
    val query = handle { repo.query(Unit).toList() }

    val save = handle { customers, new: Customer ->
        repo.addOrUpdate(customers, new).toList()
    }
    val remove = handle { customers, id: String ->
        repo.delete(customers, id).toList()
    }

    init {
        query()
    }
}
