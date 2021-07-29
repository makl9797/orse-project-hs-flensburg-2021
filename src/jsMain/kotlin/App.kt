import dev.fritz2.components.gridBox
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.params.AreaName
import kotlinx.coroutines.ExperimentalCoroutinesApi

val grid = object {
    val HEADER: AreaName = "header"
    val BODY: AreaName = "content"
    val FOOTER: AreaName = "footer"
}

@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.app(): Div {
    return div("application", "app") {
        gridBox({
            rows {
                repeat(3) { "1fr" }
            }

        }) {
            div("container", "header") {
            }
            div("container", "body") {

            }
            div("container", "footer") {

            }
        }
    }
}
