package stores

import dev.fritz2.binding.RootStore
import dev.fritz2.identification.uniqueId
import dev.fritz2.repositories.rest.restQuery
import models.Day
import models.DayResource


object DayListStore : RootStore<List<Day>>(emptyList()) {
    private const val DAY_ENDPOINT = "/overview"

    private val repo = restQuery<Day, String, Unit>(DayResource, DAY_ENDPOINT, uniqueId())
    val query = handle {
        repo.query(Unit)
    }

    init {
        query()
        SubjectListStore.save handledBy query
        BookingListStore.save handledBy query
    }
}

