
import components.app
import dev.fritz2.dom.html.render
import dev.fritz2.styling.theme.Theme
import kotlinx.browser.document
import kotlinx.coroutines.ExperimentalCoroutinesApi
import themes.OrseTheme
import dev.fritz2.dom.html.render
import kotlinx.browser.document
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun main() {
    document.bgColor = "#ABC1DE"
    render("#root") {
        Theme.use(OrseTheme)
    document.bgColor = "#293133"
    render("#root") {
        app()
    }
}
