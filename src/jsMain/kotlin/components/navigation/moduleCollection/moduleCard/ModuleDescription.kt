package components.navigation.moduleCollection.moduleCard

import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.params.FlexParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
fun RenderContext.moduleCardDescription(style: Style<FlexParams>, id: String, text: String) {
    flexBox({
        style()
    }, id = "${id}Description") {
        p {
            +text
        }
    }
}