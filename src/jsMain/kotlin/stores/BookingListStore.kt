package stores

import dev.fritz2.binding.RootStore
import dev.fritz2.components.alert
import dev.fritz2.components.toast
import dev.fritz2.identification.uniqueId
import dev.fritz2.repositories.rest.restQuery
import models.Booking
import models.BookingResource


object BookingListStore : RootStore<List<Booking>>(emptyList()) {
    private const val BOOKING_ENDPOINT = "/bookings"
    private val repo = restQuery<Booking, String, Unit>(BookingResource, BOOKING_ENDPOINT, uniqueId())
    val query = handle { repo.query(Unit) }

    val save = handleAndEmit<Booking, Unit> { bookings, new ->
        emit(Unit)
        repo.addOrUpdate(bookings, new)
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
    }
}