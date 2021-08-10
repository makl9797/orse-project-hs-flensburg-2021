package components.navigation.moduleCollection.moduleCard

import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.params.FlexParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
fun RenderContext.moduleCardName(style: Style<FlexParams>, id: String, name: String) {
    flexBox({
        style()
    }, id = "${id}Name") {
        h3 { +name }
    }
}