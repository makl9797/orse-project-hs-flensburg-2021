package modules.wrapper

import dev.fritz2.binding.Handler
import dev.fritz2.binding.RootStore
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.StyleClass
import dev.fritz2.styling.style
import kotlinx.browser.document
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.w3c.dom.Element

@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.droppable(id: String, hasDraggable: Boolean, content: Div.() -> Unit): Div {
    val droppableStyle: StyleClass = style("droppable") {
    }

    var currentDraggedItem: Element? = null
    var hoveredDroppable: Element? = null

    fun update() {
        currentDraggedItem = document.querySelector("[isDragged = true]")
        hoveredDroppable = document.getElementById(id)
    }


    val droppableStore = object : RootStore<Boolean>(false) {
        val dragOver: Handler<Unit> = handle { _, _ ->
            val event = dragovers.events.first()
            update()
            event.preventDefault()
            true
        }
        val dragEnter: Handler<Unit> = handle { _, _ ->
            update()
            this@droppable.attr("dragStatus", "hover")
            dragenters.events.first().preventDefault()
            true
        }
        val dragLeave: Handler<Unit> = handle { _, _ ->
            update()
            this@droppable.attr("dragStatus", "empty")
            false
        }
        val drop: Handler<Unit> = handle { _, _ ->
            update()
            this@droppable.attr("dragStatus", "empty")
            val oldDroppable = currentDraggedItem?.parentElement
            val replacedChild = hoveredDroppable?.replaceChild(currentDraggedItem!!, hoveredDroppable!!.firstChild!!)
            oldDroppable?.appendChild(replacedChild!!)
            false
        }
    }


    return div(droppableStyle.name, id) {
        if (hasDraggable) {
            attr("dragStatus", "full")
        } else {
            attr("dragStatus", "empty")
        }

        content()
        dragovers handledBy droppableStore.dragOver
        dragenters handledBy droppableStore.dragEnter
        dragleaves handledBy droppableStore.dragLeave
        drops handledBy droppableStore.drop
    }
}




