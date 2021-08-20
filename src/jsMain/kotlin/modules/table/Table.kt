package modules.table

import dev.fritz2.binding.storeOf
import dev.fritz2.components.dataTable
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.L
import models.Subject
import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings
import stores.data.SelectedDayStore
import stores.data.SelectedSubjectStore

class Table {
    private var count = -1

    private val type = Module.Type.TABLE

    private val defaultSettings = ModuleSettings(
        width = 60,
        height = 40,
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
                }
            }
        }
    }


}
