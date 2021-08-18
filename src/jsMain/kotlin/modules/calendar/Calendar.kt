package modules.calendar

import dev.fritz2.components.DropdownComponent.PlacementContext.right
import dev.fritz2.components.dataTable
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.lenses.asString
import dev.fritz2.lenses.buildLens
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BackgroundBlendModes.color
import dev.fritz2.styling.params.BasicParams
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.Day
import models.L
import models.L.Day.availableSubjects
import models.L.Day.bookings
import models.L.Day.day
import models.L.Day.subjects
import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings
import stores.dayListStore
import stores.dayStore

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
        moduleDescription = "Dieses modul repräsentiert einen Kalender",
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
        dataTable(rows = dayListStore, rowIdProvider = Day::day, selection = dayStore) {
            header { fixedHeader(true) }
            columns {
                column(title = "Tag") {
                    lens(day)
                    width { minmax("150px") }
                }
                column(title = "Verfügbar") {
                    lens(availableSubjects)
                    width { minmax("1fr") }
                }
            }
        }
    }
}

