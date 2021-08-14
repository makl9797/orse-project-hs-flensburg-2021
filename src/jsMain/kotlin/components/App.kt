package components

import components.content.workspace
import components.navigation.navigation
import dev.fritz2.components.clickButton
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.Address
import models.Booking
import models.Customer
import models.Subject
import modules.moduleCatalog
import stores.appStateStore
import stores.bookingStore

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
        bookingStore.data.renderEach {
            div {
                console.log(it)
            }
        }
        clickButton {
            text("New Booking")
        }.events.map {
            Booking(0.0, Customer("", Address(), "", ""), "", "", Subject())
        } handledBy bookingStore.save
        clickButton {
            text("Delete last Booking")
        }.events.map {
            "20-09-1997"
        } handledBy bookingStore.remove
    }
}

