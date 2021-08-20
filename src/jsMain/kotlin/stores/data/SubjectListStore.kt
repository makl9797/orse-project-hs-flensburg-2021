package stores.data

import dev.fritz2.binding.RootStore
import dev.fritz2.components.alert
import dev.fritz2.components.toast
import dev.fritz2.repositories.rest.restQuery
import models.data.Subject
import models.data.SubjectResource


object SubjectListStore : RootStore<List<Subject>>(listOf()) {
    private const val SUBJECT_ENDPOINT = "/subjects"

    private val repo = restQuery<Subject, String, Unit>(SubjectResource, SUBJECT_ENDPOINT, "")


    val query = handle {
        repo.query(Unit)
    }

    val save = handleAndEmit<Subject, Unit> { subjects, new ->
        val temp = repo.addOrUpdate(subjects, new)
        emit(Unit)
        temp
    }
    val remove = handle { subjects, id: String ->
        repo.delete(subjects, id)
    }

    init {
        query()
        syncBy(query)
        save handledBy toast {
            placement { top }
            background { success.main }
            hasCloseButton(false)
            content {
                alert {
                    severity { success }
                    title("Neues Objekt angelegt!")
                }
            }
        }
    }
}

