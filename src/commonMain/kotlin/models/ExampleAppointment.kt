package models

import dev.fritz2.lenses.Lenses

@Lenses
data class ExampleAppointment(
    val id: Int = 0,
    val title: String = "",
    val person: String = "",
    val date: String = "",
    val time: String = "",
    val details: String = ""
)








