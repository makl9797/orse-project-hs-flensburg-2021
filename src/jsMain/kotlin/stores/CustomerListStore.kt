package stores

import dev.fritz2.binding.RootStore
import dev.fritz2.identification.uniqueId
import dev.fritz2.repositories.rest.restQuery
import models.Customer
import models.CustomerResource


object CustomerListStore : RootStore<List<Customer>>(emptyList()) {
    private const val CUSTOMER_ENDPOINT = "/customers"

    private val repo = restQuery<Customer, String, Unit>(CustomerResource, CUSTOMER_ENDPOINT, uniqueId())

    val query = handle { repo.query(Unit) }

    val save = handle { customers, new: Customer ->
        repo.addOrUpdate(customers, new)
    }
    val remove = handle { customers, id: String ->
        repo.delete(customers, id)
    }
    val getAll = handle { customers, id: String ->

        repo.query(Unit)
    }

    init {
        query()
        syncBy(query)
    }
}
