package modules.calendar

import dev.fritz2.binding.storeOf
import dev.fritz2.components.clickButton
import dev.fritz2.components.gridBox
import dev.fritz2.components.selectField
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.button
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.FontWeights.bold
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.datetime.*
import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings
import stores.DayListStore


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
    val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
    val monthsList = Month.values().toList()
    val selectedMonth = storeOf(today.month)

    div({
        //this or child style
    }) {
        selectField(items = monthsList, value = selectedMonth) { }
    }

    gridBox({
        columns { repeat(7) { "1fr" } }

        children("button") {
            width { "4rem" }
            height { "4rem" }
            color { primary.mainContrast }
            background { color { primary.main } }
            display { flex }
            radius { small }
            css("justify-content: center")
            css("align-items: center")
        }
        children("div") {
            width { "4rem" }
            height { "4rem" }
            color { tertiary.mainContrast }
            background { color { primary.mainContrast } }
            display { flex }
            radius { small }
            fontStyle { bold }
            css("justify-content: center")
            css("align-items: center")
        }

    }) {
        div { +"Mo" }
        div { +"Di" }
        div { +"Mi" }
        div { +"Do" }
        div { +"Fr" }
        div { +"Sa" }
        div { +"So" }

        selectedMonth.data.render { month ->
            val daysInMonth = daysInMonth(month.number, false)
            val firstDayInMonth = LocalDate(today.year, month.number, 1)
            val dayOfWeekFirstDay = firstDayInMonth.dayOfWeek.isoDayNumber
            console.log(dayOfWeekFirstDay)
            for (i in 1 until dayOfWeekFirstDay) {
                div { }
            }
            for (i in 1..daysInMonth) {
                clickButton {
                    text(i.toString())
                    type { primary }
                }.events.map { LocalDate(today.year, month.number, i) } handledBy DayListStore.getDay
            }
        }

    }
    //--

}
/*
*
*  val notAvailableStyle: Style<BasicParams> = {
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
*
* */

