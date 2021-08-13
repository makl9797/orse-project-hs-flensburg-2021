import react.dom.render
import kotlinx.browser.document


fun main() {

        render(document.getElementById("root")) {
            child(Welcome::class) {
                attrs {
                    name = "Orse"
                }
            }
        }
}
