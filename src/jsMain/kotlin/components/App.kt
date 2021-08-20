package components

import components.content.workspace
import components.navigation.navigation
import dev.fritz2.components.gridBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.browser.document
import kotlinx.coroutines.ExperimentalCoroutinesApi
import modules.moduleCatalog
import org.w3c.dom.HTMLElement
import org.w3c.dom.get
import stores.app.AppStateStore

@ExperimentalCoroutinesApi
fun RenderContext.app() {
    gridBox({
        height { "100vh" }
        rows { "4.5rem auto" }
    }, id = "app") {
        AppStateStore.data.render { state ->
            navigation("navigation", state.mode, moduleCatalog)
            workspace("workspace", state.mode)
        }
    }
    val nav = document.getElementsByTagName("nav")[0] as HTMLElement
    nav.style.position = "relative"
}

