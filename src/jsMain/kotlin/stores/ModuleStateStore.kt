package stores

import dev.fritz2.binding.RootStore
import kotlinx.browser.document
import org.w3c.dom.Element
import org.w3c.dom.events.MouseEvent

object ModuleStateStore : RootStore<Element>(document.body as Element) {

    val onMouseDown = handle { model, e: MouseEvent ->
        model
    }
}