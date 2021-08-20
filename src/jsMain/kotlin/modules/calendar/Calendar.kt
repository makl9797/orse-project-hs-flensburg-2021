package modules.calendar

import dev.fritz2.binding.storeOf
import dev.fritz2.components.clickButton
import dev.fritz2.components.gridBox
import dev.fritz2.components.selectField
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.FontWeights.bold
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.datetime.*
import models.app.Module
import models.app.ModuleCard
import models.app.ModuleSettings
import stores.data.DayListStore


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
        moduleName = "Kalender",
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
        columns { repeat(7) { "3.5rem" } }
        css("justify-items: center")
        children("button") {
            width { "3.5rem" }
            height { "3.5rem" }
            color { primary.mainContrast }
            display { flex }
            radius { none }
            css("justify-content: center")
            css("align-items: center")
        }
        children("div") {
            width { "3.5rem" }
            height { "3.5rem" }
            color { tertiary.mainContrast }
            background { color { primary.mainContrast } }
            display { flex }
            radius { none }
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

        selectedMonth.data.combine(DayListStore.data) { month, dayList ->
            Pair(month, dayList)
        }.render { combinedData ->
            val daysInMonth = daysInMonth(combinedData.first.number, false)
            val firstDayInMonth = LocalDate(today.year, combinedData.first.number, 1)
            val dayOfWeekFirstDay = firstDayInMonth.dayOfWeek.isoDayNumber
            for (i in 1 until dayOfWeekFirstDay) {
                div { }
            }
            for (i in 1..daysInMonth) {

                val day = combinedData.second.firstOrNull { day ->
                    day.day.toLocalDate() == LocalDate(today.year, combinedData.first.number, i)
                }
                clickButton({
                    if (day != null) {
                        if (day.availableSubjects < 1) {
                            background { color { danger.main } }
                        } else if (day.availableSubjects in 1..3) {
                            background { color { secondary.main } }
                        } else {
                            background { color { primary.main } }
                        }
                    } else {
                        background { color { primary.main } }
                    }
                }) {
                    text(i.toString())
                    if (day != null) {
                        disabled(false)
                    } else {
                        disabled(true)
                    }
                    type { primary }
                }.events.map { LocalDate(today.year, combinedData.first.number, i) } handledBy DayListStore.getDay
            }
        }
    }
    //--

}

fun daysInMonth(month: Int, leapYear: Boolean): Int {
    val daysInMonth: Int = if (month == 4 || month == 6 || month == 9 || month == 11) {
        30
    } else if (month == 2) {
        if (leapYear) {
            29
        } else {
            28
        }
    } else {
        31
    }
    return daysInMonth
}

