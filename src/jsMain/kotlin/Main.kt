import react.dom.render
import kotlinx.browser.document

@ExperimentalJsExport
fun main() {
    render(document.getElementById("root")) {
        child(App::class) {

        }
    }
}
