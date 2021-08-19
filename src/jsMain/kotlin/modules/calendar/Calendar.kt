package modules.calendar

import dev.fritz2.components.dataTable
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.lenses.asString
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BasicParams
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
    val notAvailableStyle: Style<BasicParams> = {
        color { danger.main }
    }
    div({
        style()
    }, id = id) {
        dataTable(rows = DayListStore, rowIdProvider = Day::day, selection = SelectedDayStore) {
            header { fixedHeader(true) }
            columns({ (_, state) ->
                if (state.item.availableSubjects < 1) {
                    notAvailableStyle()
                }
            }) {
                column(title = "Tag") {
                    lens(L.Day.day)
                    width { minmax("150px") }
                }
                column(title = "Verfügbar") {
                    lens(L.Day.availableSubjects.asString())
                    width { minmax("1fr") }
                }
            }
        }
    }
}

