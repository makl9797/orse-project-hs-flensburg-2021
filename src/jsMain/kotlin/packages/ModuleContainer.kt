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
        module("exampleTableModule") {
            appointmentTable(id = "exampleTable")
        }
        module("exampleTableModule") {
            appointmentTable(id = "exampleTable")
        }
        module("exampleTableModule") {
            appointmentTable(id = "exampleTable")
        }
        module("exampleTableModule") {
            appointmentTable(id = "exampleTable")
        }
        module("exampleTableModule") {
            appointmentTable(id = "exampleTable")
        }
    }
}