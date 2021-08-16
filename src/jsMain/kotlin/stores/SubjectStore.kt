package stores

import dev.fritz2.binding.RootStore
import dev.fritz2.identification.uniqueId
import dev.fritz2.repositories.rest.restQuery
import models.Subject
import models.SubjectResource

val subjectStore = SubjectStore(emptyList(), id = "bookings")
const val SUBJECT_ENDPOINT = "/subjects"

class SubjectStore(init: List<Subject>, id: String) : RootStore<List<Subject>>(init, id = id) {
    private val repo = restQuery<Subject, String, Unit>(SubjectResource, SUBJECT_ENDPOINT, uniqueId())
    val query = handle { repo.query(Unit).toList() }

    val save = handle<Subject> { subjects, new ->
        repo.addOrUpdate(subjects, new).toList()
    }
    val remove = handle { subjects, id: String ->
        repo.delete(subjects, id).toList()
    }

    init {
        query()
    }
}
