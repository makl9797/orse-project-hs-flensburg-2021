package modules.wrapper

import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.params.FlexParams
import kotlinx.coroutines.ExperimentalCoroutinesApi
import modules.appointmentTable

@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.moduleContainer(style: FlexParams.() -> Unit, id: String): Div {
    return flexBox(style, id = id) {
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
    }
}