package components

import dev.fritz2.components.navBar
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.navbar(editMode: Boolean) {
    return navBar({
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
