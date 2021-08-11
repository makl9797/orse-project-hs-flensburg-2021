package modules.info

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import dev.fritz2.styling.params.BoxParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings

class InfoBox {
    private var count = 0

    private val id = "infoBox${count}"

    private val type = Module.Type.INFOBOX

    private val defaultSettings = ModuleSettings(
        width = 700.0,
        height = 250.0,
        parentX = 0.0,
        parentY = 0.0
    )
    private val card = ModuleCard(
        moduleName = "Informationen",
        moduleDescription = "Dieses Modul stellt Daten in einem größeren Fenster dar. Außerdem können noch zusätzliche Informationen dargestellt werden.",
        exampleImageSrc = "https://via.placeholder.com/150?text=Module+Example+PicPlaceholder"
    )

    fun createModule(): Module {
        count++
        return Module(id, type, defaultSettings, card)
    }
}

@ExperimentalCoroutinesApi
fun RenderContext.infoBox(id: String, style: Style<BoxParams>) {
    div({
        style()
        background { color { primary.main } }
    }, id = id) {
    }
}


