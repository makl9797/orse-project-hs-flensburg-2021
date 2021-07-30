package packages

import dev.fritz2.binding.storeOf
import dev.fritz2.components.dataTable
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.lenses.asString
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.ExampleAppointment
import models.L


@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.appointmentTable(id: String) {
    val exampleAppointments = listOf(
        ExampleAppointment(0, "Termin1", "Person1", "01.01.2022", "12:00", "Detailinformationen1"),
        ExampleAppointment(1, "Termin2", "Person2", "01.01.2022", "12:00", "Detailinformationen2"),
        ExampleAppointment(1, "Termin2", "Person2", "01.01.2022", "12:00", "Detailinformationen2"),
        ExampleAppointment(1, "Termin2", "Person2", "01.01.2022", "12:00", "Detailinformationen2"),
        ExampleAppointment(1, "Termin2", "Person2", "01.01.2022", "12:00", "Detailinformationen2"),
        ExampleAppointment(1, "Termin2", "Person2", "01.01.2022", "12:00", "Detailinformationen2"),
        ExampleAppointment(1, "Termin2", "Person2", "01.01.2022", "12:00", "Detailinformationen2")
    )

    return dataTable(
        {
        },
        id = id,
        rows = storeOf(exampleAppointments),
        rowIdProvider = ExampleAppointment::id
    ) {
        columns {
            column(title = "Id") { lens(L.ExampleAppointment.id.asString()) }
            column(title = "Titel") { lens(L.ExampleAppointment.title) }
            column(title = "Person") { lens(L.ExampleAppointment.person) }
            column(title = "Datum") { lens(L.ExampleAppointment.date) }
            column(title = "Zeit") { lens(L.ExampleAppointment.time) }
            column(title = "Details") { lens(L.ExampleAppointment.details) }
        }
    }

}