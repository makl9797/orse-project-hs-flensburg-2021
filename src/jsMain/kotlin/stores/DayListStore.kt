package stores

import dev.fritz2.binding.RootStore
import dev.fritz2.identification.uniqueId
import dev.fritz2.repositories.rest.restQuery
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import models.Day
import models.DayResource


object DayListStore : RootStore<List<Day>>(emptyList()) {
    private const val DAY_ENDPOINT = "/overview"

    private val repo = restQuery<Day, String, Unit>(DayResource, DAY_ENDPOINT, uniqueId())
    val query = handle {
        repo.query(Unit)
    }
    val getDay = handleAndEmit<LocalDate, Day> { model, date ->
        val day = model.filter {
            it.day.toLocalDate() == date
        }.first()
        emit(day)
        model
    }

    init {
        query()
        SubjectListStore.save handledBy query
        BookingListStore.save handledBy query
        getDay handledBy SelectedDayStore.update
    }
}

