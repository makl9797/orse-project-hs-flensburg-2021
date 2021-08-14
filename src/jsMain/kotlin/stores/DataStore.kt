package stores

import api.Network
import dev.fritz2.binding.RootStore
import dev.fritz2.components.randomId
import dev.fritz2.repositories.rest.restEntity
import dev.fritz2.tracking.tracker
import models.Appointment
import models.AppointmentResource

val dataStore: DataStore = DataStore(Appointment("", 0, ""))

class DataStore(init: Appointment) : RootStore<Appointment>(init) {
    private val appointmentRepo = restEntity(AppointmentResource, "/appointment", randomId())
    private val api = Network()
    private val tracking = tracker()

    val save = handle<Appointment> { model, new ->
        appointmentRepo.load(new.appointmentId)
    }

    val getAppointment = handle {
        api.getAppointmentById("30ae5813-d5dd-47ce-8b5f-3a72df23779d")
    }
}