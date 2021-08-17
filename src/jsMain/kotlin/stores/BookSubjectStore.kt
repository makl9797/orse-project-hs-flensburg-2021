package stores

import dev.fritz2.binding.RootStore
import models.Subject

val bookSubjectStore = BookSubjectStore(null, "bookSubjectStore")

class BookSubjectStore(init: Subject?, id: String) : RootStore<Subject?>(init, id = id) {
}