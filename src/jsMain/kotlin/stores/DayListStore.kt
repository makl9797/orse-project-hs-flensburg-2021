package stores

import dev.fritz2.binding.RootStore
import dev.fritz2.identification.uniqueId

import dev.fritz2.repositories.rest.restQuery
import models.*

val dayListStore = DayListStore(emptyList(), id = "days")
const val DAY_ENDPOINT = "/overview"

class DayListStore(init: List<Day>, id: String) : RootStore<List<Day>>(init, id = id) {
    private val repo = restQuery<Day, String, Unit>(DayResource, DAY_ENDPOINT, uniqueId())
    val query = handle { repo.query(Unit) }

    init {
        query()
    }
}