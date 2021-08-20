package stores.data

import dev.fritz2.binding.RootStore
import models.data.Booking
import models.data.Day
import models.data.Subject

val testList = listOf(
    Subject("1", "Name1", "Beschreibung1", "Typ1"),
    Subject("2", "Name2", "Beschreibung2", "Typ2"),
    Subject("3", "Name3", "Beschreibung3", "Typ3"),
    Subject("4", "Name4", "Beschreibung4", "Typ4"),
    Subject("5", "Name5", "Beschreibung5", "Typ5")
).shuffled()

val exampleDay = Day("2021-09-20", emptyList<Booking>().toMutableList(), testList.toMutableList(), testList.count())

object SelectedDayStore : RootStore<Day?>(null) {

    init {
        syncBy(BookingStore.updateStartTime)
    }

}
