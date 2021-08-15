package stores

import dev.fritz2.binding.RootStore
import dev.fritz2.identification.uniqueId
import dev.fritz2.repositories.rest.restQuery
import models.SubjectLenses
import models.SubjectResource

val subjectStore = SubjectStore(emptyList(), id = "bookings")
const val SUBJECT_ENDPOINT = "/subjects"

class SubjectStore(init: List<SubjectLenses>, id: String) : RootStore<List<SubjectLenses>>(init, id = id) {
    private val repo = restQuery<SubjectLenses, String, Unit>(SubjectResource, SUBJECT_ENDPOINT, uniqueId())
    val query = handle { repo.query(Unit).toList() }

    val save = handle<SubjectLenses> { subjects, new ->
        repo.addOrUpdate(subjects, new).toList()
    }
    val remove = handle { subjects, id: String ->
        repo.delete(subjects, id).toList()
    }

    init {
        query()
    }
}
