package components.navigation.moduleCollection.moduleCard

import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.params.FlexParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.ModuleSettings


@ExperimentalCoroutinesApi
fun RenderContext.moduleCardSettings(style: Style<FlexParams>, id: String, settings: ModuleSettings) {

    flexBox({
        justifyContent { spaceBetween }
        alignItems { center }
        margins { horizontal { "0.5rem" } }
        style()
    }, id = "${id}Settings") {
        moduleCardSettingsInput(id = id, startValue = settings.width, "Weite", "W")
        moduleCardSettingsInput(id = id, startValue = settings.height, "Höhe", "H")
        moduleCardSettingsInput(id = id, startValue = settings.parentX, "Position X", "X")
        moduleCardSettingsInput(id = id, startValue = settings.parentY, "Position Y", "Y")
    }
}