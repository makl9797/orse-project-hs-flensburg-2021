package stores.data

import dev.fritz2.binding.RootStore
import models.data.Day


/**
 * SelectedDayStore
 *
 * Contains the current selected day in the calendar module
 */
object SelectedDayStore : RootStore<Day?>(null) {

    init {
        syncBy(BookingStore.updateStartTime)
    }

}
