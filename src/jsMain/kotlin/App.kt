import dev.fritz2.components.box
import dev.fritz2.components.gridBox
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import packages.navbar


@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.app(): Div {
    return div("application", "app") {

        gridBox({
            rows {
                repeat(2) { "1fr" }
            }


        }) {
            navbar()
            box({
                background { color { primary.main } }
                color { primary.mainContrast }
            }) {
                +"Body"
            }
        }
    }
}
