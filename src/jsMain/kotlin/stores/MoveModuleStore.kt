package stores

import dev.fritz2.binding.RootStore
import org.w3c.dom.Element
import org.w3c.dom.events.MouseEvent

class MoveModuleStore(init: Element) : RootStore<Element>(init) {


    val onMouseDown = handle { model, e: MouseEvent ->
        model
    }

}