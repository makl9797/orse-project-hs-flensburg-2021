package components.navigation.subjectModal

import dev.fritz2.binding.SimpleHandler
import dev.fritz2.binding.storeOf
import dev.fritz2.components.flexBox
import dev.fritz2.components.formControl
import dev.fritz2.components.pushButton
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.values
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.L
import models.Subject
import stores.SubjectListStore


@ExperimentalCoroutinesApi
fun RenderContext.addSubjectForm(id: String, modalClose: SimpleHandler<Unit>) {
    val subjectStore =
        storeOf(Subject(name = "", description = "", type = ""))
    val idStore = subjectStore.sub(L.Subject._id)
    val nameStore = subjectStore.sub(L.Subject.name)
    val descriptionStore = subjectStore.sub(L.Subject.description)
    val typeStore = subjectStore.sub(L.Subject.type)
    val types = mutableListOf("Strandkorb", "Fahrrad")

    flexBox({
        wrap { wrap }
        justifyContent { flexEnd }
    }, id = id + "Form") {
        formControl {
            label("Objektname")
            inputField(value = nameStore) {
                placeholder("Name des Objekts...")
            }
        }
        formControl({
            margins { top { small } }
        }) {
            label("Objektbeschreibung")
            textArea {
                value(descriptionStore.data)
                placeholder("Beschreibe das Objekt...")
                resizeBehavior { none }
                events {
                    changes.values() handledBy descriptionStore.update
                }
            }
        }

        formControl({
            margins { top { small } }
        }) {
            label("Objekttyp")
            selectField(items = types, value = typeStore) {
                placeholder("Wähle den Objekttyp...")
            }
        }
        pushButton({
            margins { top { small } }
        }) {
            variant { outline }
            type { success }
            text("Hinzufügen")
            events {
                clicks.events.map {
                    Subject(
                        name = subjectStore.current.name,
                        description = subjectStore.current.description,
                        type = subjectStore.current.type
                    )
                } handledBy SubjectListStore.save
            }
        }
    }
}