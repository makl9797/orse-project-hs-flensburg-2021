package components.navigation.subjectModal

import dev.fritz2.binding.SimpleHandler
import dev.fritz2.components.clickButton
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.addSubjectFooter(id: String, modalClose: SimpleHandler<Unit>) {
    flexBox({
        justifyContent { flexEnd }
    }, id = id + "Footer") {
        clickButton({
            margins { right { tiny } }
        }) {
            variant { outline }
            text("Schließen")
        } handledBy modalClose
    }
}