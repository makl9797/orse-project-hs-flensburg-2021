package components

import components.content.workspace
import components.navigation.navigation
import dev.fritz2.components.clickButton
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.identification.uniqueId
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
        val booking = Booking(
            price = 0.0,
            customer = Customer(uniqueId(), Address(), "", ""),
            startTime = "1970-01-01",
            endTime = "1970-01-01",
            subject = Subject()
        )
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

