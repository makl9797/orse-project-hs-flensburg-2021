package components.navigation.moduleCollection.moduleCard

import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.img
import dev.fritz2.styling.params.FlexParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
fun RenderContext.moduleCardImage(style: Style<FlexParams>, id: String, src: String, alt: String) {
    flexBox({
        width { "9.25rem" }
        style()
    }, id = "${id}Image") {
        img({
            radius { "0.75rem" }
        }) {
            src(src)
            alt(alt)
        }
    }

}