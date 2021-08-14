package components

import components.content.workspace
import components.navigation.navigation
import dev.fritz2.components.clickButton
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.identification.uniqueId
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.Booking
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
        val booking = Booking(_id = uniqueId())
        clickButton {
            text("New Booking")
        }.events.map {
            booking
        } handledBy bookingStore.save
        clickButton {
            text("Delete last Booking")
        }.events.map {
            booking._id
        } handledBy bookingStore.remove
        clickButton {
            text("Show Bookings")
        } handledBy bookingStore.query
    }
}

