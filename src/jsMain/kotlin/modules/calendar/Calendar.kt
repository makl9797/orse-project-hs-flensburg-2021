package modules.calendar

import dev.fritz2.components.dataTable
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.lenses.asString
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.Day
import models.L
import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings
import stores.DayListStore
import stores.SelectedDayStore

class Calendar {
    private var count = -1
    private val type = Module.Type.CALENDAR
    private val defaultSettings = ModuleSettings(
        width = 60,
        height = 40,
        startX = 1,
        startY = 1
    )
    private val card = ModuleCard(
        moduleName = "Calendar",
        moduleDescription = "Dieses modul repräsentiert ein Kalender",
        exampleImageSrc = "https://via.placeholder.com/150?text=Module+Example+PicPlaceholder"
    )

    fun createModule(settings: ModuleSettings = defaultSettings, card: ModuleCard = this.card): Module {
        count++
        val id = "calendar${count}"
        return Module(id, type, settings, card)
    }
}

@ExperimentalCoroutinesApi
fun RenderContext.calendar(id: String, style: Style<BoxParams>) {
    div({
        style()
        background { color { primary.main } }

    }, id = id) {
        DayListStore.data.render {
            dataTable(rows = DayListStore, rowIdProvider = Day::day, selection = SelectedDayStore) {
                columns {
                    column(title = "Tag") { lens(L.Day.day) }
                    column(title = "Verfügbar") { lens(L.Day.availableSubjects.asString()) }
                }
            }
        }

    }

}

