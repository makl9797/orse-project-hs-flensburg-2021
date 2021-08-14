package stores

import api.Network
import dev.fritz2.binding.RootStore
import dev.fritz2.binding.storeOf
import dev.fritz2.remote.getBody
import models.L
import models.SubjectLenses

val dataStore: DataStore = DataStore(SubjectLenses("", "", ""))

class DataStore(init: SubjectLenses) : RootStore<SubjectLenses>(init) {
    val api = Network()
    val test = storeOf(SubjectLenses("", "", ""))
    val test2 = test.sub(L.SubjectLenses.subjectId)

    val getSubject = handle {
        val string: String = api.orseApi.get("/subject/30ae5813-d5dd-47ce-8b5f-3a72df23779d").getBody()
        val json = JSON.parse<SubjectLenses>(string)
        json
    }
}