package packages

import dev.fritz2.components.box
import dev.fritz2.components.gridBox
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.app(): Div {
    return div({
        height { "100vh" }
    }, id = "app") {
        gridBox({
            rows { "72px auto" }
        }) {
            box({
                grid {
                    row {
                        start { "1" }

                    }
                }
            }) {
                navbar()
            }

            moduleContainer("modules")

        }
    }
}
