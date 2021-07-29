package packages

import dev.fritz2.components.navBar
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.navbar(): Div {
    return div("container", "navbar") {
        navBar({
            background { color { secondary.main } }
            color { secondary.mainContrast }
        }) {
            brand {
                h1 { +"Orse" }
            }
            actions {
                mainMenu()
            }
        }
    }
}