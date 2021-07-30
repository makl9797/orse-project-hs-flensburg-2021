package packages

import dev.fritz2.components.gridBox
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.app(): Div {
    return div("application", "packages.app") {

        gridBox({
            rows {
                repeat(2) { "auto" }
            }


        }) {
            navbar()
            module("exampleTableModule", 70, 30) {
                appointmentTable(id = "exampleTable")
            }

        }
    }
}
