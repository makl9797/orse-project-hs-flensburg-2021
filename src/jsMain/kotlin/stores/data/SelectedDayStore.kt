package stores.data

import dev.fritz2.binding.RootStore
import models.data.Day


object SelectedDayStore : RootStore<Day?>(null) {

    init {
        syncBy(BookingStore.updateStartTime)
    }

}
