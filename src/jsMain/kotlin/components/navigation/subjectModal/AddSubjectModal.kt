package components.navigation.subjectModal

import components.navigation.modalTitle
import dev.fritz2.binding.SimpleHandler
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.addSubjectModal(id: String, modalClose: SimpleHandler<Unit>) {
    div(id = id) {
        modalTitle(id, "Objekt hinzufügen")

        flexBox({
            paddings { vertical { "1rem" } }
            direction { column }
        }, id = id + "Body") {
            addSubjectForm(id)
        }

        addSubjectFooter(id, modalClose)
    }
}