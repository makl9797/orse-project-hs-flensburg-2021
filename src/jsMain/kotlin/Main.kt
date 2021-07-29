import dev.fritz2.dom.html.render
import dev.fritz2.styling.theme.Theme
import kotlinx.browser.document
import kotlinx.coroutines.ExperimentalCoroutinesApi
import themes.OrseTheme


@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun main() {
    document.bgColor = "#ABC1DE"
    render("#root") {
        Theme.use(OrseTheme)
        app()
    }
}
