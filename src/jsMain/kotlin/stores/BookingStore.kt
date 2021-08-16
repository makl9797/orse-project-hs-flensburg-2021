package stores

import dev.fritz2.binding.RootStore
import dev.fritz2.identification.uniqueId
import dev.fritz2.repositories.rest.restQuery
import models.Booking
import models.BookingResource

val bookingsStore = BookingStore(emptyList(), id = "bookings")
const val BOOKING_ENDPOINT = "/bookings"

class BookingStore(init: List<Booking>, id: String) : RootStore<List<Booking>>(init, id = id) {
    private val repo = restQuery<Booking, String, Unit>(BookingResource, BOOKING_ENDPOINT, uniqueId())
    val query = handle { repo.query(Unit).toList() }

    val save = handle { bookings, new: Booking ->
        repo.addOrUpdate(bookings, new).toList()
    }
    val remove = handle { bookings, id: String ->
        repo.delete(bookings, id).toList()
    }

    init {
        query()
    }
}