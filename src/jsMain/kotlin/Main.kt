import components.app
import dev.fritz2.dom.html.render
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun main() {
    render("#root") {
        app()
    }
}
