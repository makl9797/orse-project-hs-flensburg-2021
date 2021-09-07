package stores.data

import dev.fritz2.binding.RootStore
import models.data.Subject

/**
 * SelectedSubjectStore
 *
 * Contains the current selected subject in the table module
 */

object SelectedSubjectStore : RootStore<Subject?>(null) {
    init {
        syncBy(BookingStore.updateSubject)
    }
}
