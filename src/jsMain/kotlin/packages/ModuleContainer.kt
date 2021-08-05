package packages

import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.moduleContainer(id: String): Div {
    return flexBox({
        wrap { wrap }
        grid {
            row {
                start { "2" }
            }
        }
        overflowX { hidden }
    }, id = id) {
        droppable("droppable1", true) {
            module("tableModule1", "Beispieltabelle 1") {
                appointmentTable(id = "table1")
            }
        }
        droppable("droppable2", true) {
            module("tableModule2", "Beispieltabelle 2") {
                appointmentTable(id = "table2")
            }
        }
        droppable("droppable3", true) {
            module("tableModule3", "Beispieltabelle 3") {
                appointmentTable(id = "table3")
            }
        }
        droppable("droppable4", true) {
            module("tableModule4", "Beispieltabelle 4") {
                appointmentTable(id = "table4")
            }
        }
        droppable("droppable5", true) {
            module("tableModule5", "Beispieltabelle 5") {
                appointmentTable(id = "table5")
            }
        }
        droppable("droppable6", false) {

        }
    }
}