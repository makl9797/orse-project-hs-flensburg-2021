package components

import dev.fritz2.components.box
import dev.fritz2.components.gridBox
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.OverflowXValues
import kotlinx.coroutines.ExperimentalCoroutinesApi
import modules.wrapper.moduleContainer


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

            moduleContainer({
                wrap { wrap }
                grid {
                    row {
                        start { "2" }
                    }
                }
                overflowX { OverflowXValues.hidden }
            }, "modules")

        }
    }
}



