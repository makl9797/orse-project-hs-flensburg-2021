package stores.data

import dev.fritz2.binding.RootStore
import models.data.Subject

object SelectedSubjectStore : RootStore<Subject?>(null) {
    init {
        syncBy(BookingStore.updateSubject)
    }
}
