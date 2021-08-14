package api

import dev.fritz2.remote.getJson
import dev.fritz2.remote.http
import models.Appointment

class Network {
    val orseApi = http("http://localhost:8080")
        .acceptJson()
        .contentType("application/json")

    suspend fun getAppointmentById(id: String): Appointment {
        return orseApi.get("/appointment?id=$id").getJson() as Appointment
    }

    suspend fun createAppointment(info: String, date: Long) {
        orseApi.post("/appointment?info=$info&date=$date").status
    }

}