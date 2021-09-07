package stores.app

import dev.fritz2.binding.RootStore
import kotlinx.browser.document
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.MouseEvent

/**
 * ModuleStateStore
 *
 * Contains the state of a single module.
 */

object ModuleStateStore : RootStore<HTMLElement>(document.body!!) {
    private val offset = mutableListOf(0, 0)
    var isDown = false

    val onMouseDown = handle { model, e: MouseEvent ->
        isDown = true
        offset.add(model.offsetLeft - e.clientX)
        offset.add(model.offsetTop - e.clientY)
        model
    }

    val onMouseUp = handle { model, e: MouseEvent ->
        isDown = false
        model
    }

    val onMouseMove = handle { model, e: MouseEvent ->
        e.preventDefault()
        if (isDown) {
            model.style.left = ("${e.clientX + offset[0]}px")
            model.style.top = ("${e.clientY + offset[1]}px")
        }
        model
    }
}