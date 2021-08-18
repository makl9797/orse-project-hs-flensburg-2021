package stores

import dev.fritz2.binding.RootStore
import models.*


object BookingStore : RootStore<Booking>(
    Booking(
        price = 0.0,
        customer = Customer(
            address = Address(
                street = "",
                city = "",
                zip = 0
            ),
            firstname = "",
            lastname = ""
        ),
        startTime = "",
        endTime = "",
        subject = Subject(
            name = "",
            description = "",
            type = ""
        )
    )
) {
    val startTimeStore = BookingStore.sub(L.Booking.startTime)
    val endTimeStore = BookingStore.sub(L.Booking.endTime)
    val priceStore = BookingStore.sub(L.Booking.price)
    private val localCustomerStore = BookingStore.sub(L.Booking.customer)
    private val localSubjectStore = BookingStore.sub(L.Booking.subject)

    val updateStartTime = startTimeStore.handle { _, new: Day? ->
        new?.day ?: "1970-01-01"
    }
    val updateCustomer = localCustomerStore.handle { model, new: Customer? ->
        new ?: model
    }
    val updateSubject = localSubjectStore.handle { _, new: Subject? ->
        new ?: Subject(name = "", description = "", type = "")
    }

}