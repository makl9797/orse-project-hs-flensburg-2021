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
        style()
    }, id = "${id}Settings") {

    }
}