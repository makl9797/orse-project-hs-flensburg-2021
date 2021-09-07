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
    // count is used for id generation if we had more than one calendar modules
    private var count = -1
    // define the type of the current module
    private val type = Module.Type.CALENDAR
    // define the default module settings
    private val defaultSettings = ModuleSettings(
        width = 26,
        height = 30,
        startX = 1,
        startY = 1
    )
    // define the ModuleCard
    private val card = ModuleCard(
        moduleName = "Kalender",
        moduleDescription = "Dieses modul repräsentiert einen Kalender",
        exampleImageSrc = "https://via.placeholder.com/150?text=Module+Example+PicPlaceholder"
    )
    // create the module with settings and card
    fun createModule(settings: ModuleSettings = defaultSettings, card: ModuleCard = this.card): Module {
        count++
        val id = "calendar${count}"
        return Module(id, type, settings, card)
    }
}

@ExperimentalCoroutinesApi
fun RenderContext.calendar(id: String, style: Style<BoxParams>) {
    val today = Clock.System.todayAt(TimeZone.currentSystemDefault())   // get the current day
    val monthsList = Month.values().toList()                            // create a list with all months
    val selectedMonth = storeOf(today.month)                            // the default-selected month is the current month
    div({ style() }, id = "${id}Box") {                                 // create the calendar-module as a div
        div({
        }, id = "${id}MonthSelector") {
            selectField(items = monthsList, value = selectedMonth) { }  // create month selector
        }
                                                                        // style the calendar module
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
            }                                                           // end of styling

        }, id = "${id}DaySelector") {                                   // create day-selector
            div { +"Mo" }
            div { +"Di" }
            div { +"Mi" }
            div { +"Do" }
            div { +"Fr" }
            div { +"Sa" }
            div { +"So" }

            // Render the Data of selected month and the day list of this month
            selectedMonth.data.combine(DayListStore.data) { month, dayList ->
                Pair(month, dayList)
            }.render { combinedData ->
                val daysInMonth = daysInMonth(combinedData.first.number, false)                  // get count of days in selected month
                val firstDayInMonth = LocalDate(today.year, combinedData.first.number, 1)     // get the first day in selected month
                val dayOfWeekFirstDay = firstDayInMonth.dayOfWeek.isoDayNumber                           // get the day of week, of the first day in selected month
                // fill up, to the first day in month, the calendar
                for (i in 1 until dayOfWeekFirstDay) {
                    div { }
                }
                // set every day
                for (i in 1..daysInMonth) {
                    // create a day
                    val day = combinedData.second.firstOrNull { day ->
                        day.day.toLocalDate() == LocalDate(today.year, combinedData.first.number, i)
                    }
                    // every day is a button
                    clickButton({
                        if (day != null) {
                            if (day.availableSubjects < 1) {            // define the style of each day in context of availability
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
                        // if day is pushed then disable
                        text(i.toString())
                        if (day != null) {
                            disabled(false)
                        } else {
                            disabled(true)
                        }
                        type { primary }
                     // see DayListStore
                    }.events.map { LocalDate(today.year, combinedData.first.number, i) } handledBy DayListStore.getDay
                }
            }
        }
    }
}

// returns the count of days in a month
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

