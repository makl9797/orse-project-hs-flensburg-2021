package modules.info

import dev.fritz2.components.box
import dev.fritz2.components.clickButton
import dev.fritz2.components.flexBox
import dev.fritz2.components.formControl
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import dev.fritz2.styling.theme.ColorScheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings
import stores.bookSubjectStore

class InfoBox {
    private var count = -1

    private val type = Module.Type.INFOBOX

    private val defaultSettings = ModuleSettings(
        width = 60,
        height = 40,
        startX = 1,
        startY = 1
    )
    private val card = ModuleCard(
        moduleName = "Informationen",
        moduleDescription = "Dieses Modul stellt Daten in einem größeren Fenster dar. Außerdem können noch zusätzliche Informationen dargestellt werden.",
        exampleImageSrc = "https://via.placeholder.com/150?text=Module+Example+PicPlaceholder"
    )

    fun createModule(settings: ModuleSettings = defaultSettings, card: ModuleCard = this.card): Module {
        count++
        val id = "infoBox${count}"
        return Module(id, type, settings, card)
    }
}

@ExperimentalCoroutinesApi
fun RenderContext.infoBox(id: String, style: Style<BoxParams>) {
    div({
        style()
        background { color { primary.main } }
    }, id = id) {
        bookSubjectStore.data.render { subject ->
            flexBox({
                direction { row }
            }) {
                box {
                    formControl {
                        label("Subject Name:"+subject?.name)
                    }}
                box { formControl {
                    label("Type:" + subject?.type)
                }}
                box { formControl {
                    label("Start Date: ")
                    inputField {
                        type("date check in")
                        placeholder("date")
                    }
                }}
                box { formControl {
                    label("Start Date: ")
                    inputField {
                        type("date check out")
                        placeholder("date")
                    }
                }}
                box { formControl {
                    label("Price: 5.0 Euro")
                }}
                box {
                    clickButton {
                        text("Buchen")
                        type { ColorScheme("#00A848","#2D3748","#E14F2A", "#2D3748") }
                    }}
            }}

    }
}
