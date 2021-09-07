package stores.data

import dev.fritz2.binding.RootStore
import dev.fritz2.components.alert
import dev.fritz2.components.toast
import dev.fritz2.identification.uniqueId
import dev.fritz2.repositories.rest.restQuery
import models.data.Booking
import models.data.BookingResource

/**
 * BookingListStore
 *
 * Communicates with the database via the endpoint /bookings
 */

object BookingListStore : RootStore<List<Booking>>(emptyList()) {
    private const val BOOKING_ENDPOINT = "/bookings"
    private val repo = restQuery<Booking, String, Unit>(BookingResource, BOOKING_ENDPOINT, uniqueId())
    val query = handle { repo.query(Unit) }

    val save = handleAndEmit<Booking, Unit> { bookings, new ->
        val temp = repo.addOrUpdate(bookings, new)
        emit(Unit)
        temp
    }
    val remove = handle { bookings, id: String ->
        repo.delete(bookings, id)
    }

    init {
        query()
        syncBy(query)
        save handledBy toast {
            placement { bottomRight }
            background { success.main }
            hasCloseButton(false)
            content {
                alert {
                    severity { success }
                    title("Buchung hinzugefügt!")
                }
            }
        }
        save handledBy BookingStore.reset
    }
}