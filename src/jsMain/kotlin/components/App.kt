package components

import components.content.workspace
import components.navigation.navigation
import dev.fritz2.components.clickButton
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import modules.moduleCatalog
import stores.appStateStore
import stores.dataStore

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
        dataStore.data.renderElement {
            div {
                console.log(it)
            }
        }
        clickButton {
            text("Test")
        } handledBy dataStore.getSubject
    }
}

