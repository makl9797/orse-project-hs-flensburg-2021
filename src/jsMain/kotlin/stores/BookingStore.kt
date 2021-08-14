package stores

import dev.fritz2.binding.RootStore
import dev.fritz2.identification.uniqueId
import dev.fritz2.repositories.rest.restQuery
import models.Booking
import models.BookingResource

val bookingStore = BookingStore(emptyList(), id = "bookings")
const val BOOKING_ENDPOINT = "/bookings"

class BookingStore(init: List<Booking>, id: String) : RootStore<List<Booking>>(init, id = id) {
    private val repo = restQuery<Booking, String, Unit>(BookingResource, BOOKING_ENDPOINT, uniqueId())
    private val query = handle { repo.query(Unit) }

    val save = handle<Booking> { bookings, new ->
        repo.addOrUpdate(bookings, new)
    }
    val remove = handle { bookings, id: String ->
        repo.delete(bookings, id)
    }

    init {
        query()
    }
}