package modules.table

import dev.fritz2.binding.storeOf
import dev.fritz2.components.clickButton
import dev.fritz2.components.dataTable
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.app.Module
import models.app.ModuleCard
import models.app.ModuleSettings
import models.data.L
import models.data.Subject
import stores.data.SelectedDayStore
import stores.data.SelectedSubjectStore
import stores.data.SubjectListStore

class Table {
    private var count = -1

    private val type = Module.Type.TABLE

    private val defaultSettings = ModuleSettings(
        width = 75,
        height = 30,
        startX = 1,
        startY = 1
    )
    private val card = ModuleCard(
        moduleName = "Tabelle",
        moduleDescription = "Dieses Modul stellt Daten in einer Tabelle dar.",
        exampleImageSrc = "https://via.placeholder.com/150?text=Module+Example+PicPlaceholder"
    )

    fun createModule(settings: ModuleSettings = defaultSettings, card: ModuleCard = this.card): Module {
        count++
        val id = "table${count}"
        return Module(id, type, settings, card)
    }
}

@ExperimentalCoroutinesApi
fun RenderContext.table(id: String, style: Style<BoxParams>) {
    SelectedDayStore.data.renderElement { day ->
        var availableSubjectsStore = storeOf<List<Subject>>(emptyList())
        if (day != null) {
            availableSubjectsStore = storeOf(day.subjects)
        }
        div({
            style()
        }, id = id) {
            dataTable(
                rows = availableSubjectsStore,
                rowIdProvider = Subject::_id,
                selection = SelectedSubjectStore
            ) {
                columns {
                    column(title = "Name") { lens(L.Subject.name) }
                    column(title = "Beschreibung") { lens(L.Subject.description) }
                    column(title = "Typ") { lens(L.Subject.type) }
                    column {
                        content { (_, state), _, _ ->
                            clickButton({ width { "100%" } }) {
                                text("Entfernen")
                            }.events.map { state.item._id } handledBy SubjectListStore.remove
                        }
                    }
                }
            }
        }
    }


}
