package components

import components.content.workspace
import components.navigation.navigation
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import modules.moduleCatalog
import stores.appStateStore
import stores.bookingsStore
import stores.customersStore

@ExperimentalCoroutinesApi
fun RenderContext.app() {
    flexBox({
        height { "100vh" }
        direction { column }
    }, id = "app") {
        appStateStore.data.render { state ->
            navigation("navigation", state.mode, moduleCatalog)
            workspace("workspace", state.mode)
        }
    }
    div {
        bookingsStore.data.renderEach {
            div {
                console.log(it)
            }
        }
    }
    customersStore.data.renderEach {
        console.log(it)
        div { }
    }
}

