package components.navigation.moduleCollection

import dev.fritz2.binding.SimpleHandler
import dev.fritz2.components.clickButton
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.moduleCollectionFooter(id: String, modalClose: SimpleHandler<Unit>) {
    flexBox({
        justifyContent { flexEnd }
    }, id = id) {
        clickButton({
            margins { right { tiny } }
        }) {
            variant { outline }
            text("Schließen")
        } handledBy modalClose
    }
}