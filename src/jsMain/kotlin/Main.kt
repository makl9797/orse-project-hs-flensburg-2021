import dev.fritz2.dom.html.render
import kotlinx.browser.document
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun main() {
    document.bgColor = "#293133"
    render("#root") {
        app()
    }
}
