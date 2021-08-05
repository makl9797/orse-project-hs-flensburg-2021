package packages

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
import org.w3c.dom.asList

@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.droppable(id: String, hasDraggable: Boolean, content: Div.() -> Unit): Div {
    val droppableStyle: StyleClass = style("droppable") {
        width { "392px" }
        height { "346px" }
    }

    var draggable: Element? = null
    var droppable: Element? = null
    var afterElement: Element? = null

    fun updatedDragAndDrop() {
        draggable = document.querySelector("[isDragged = true]")
        droppable = document.getElementById(id)
    }

    fun getDragAfterElement(droppable: Element, y: Int): Element {
        val draggableElements =
            document.querySelectorAll("[isDragged = false]").asList().map { node -> node as Element }

        return draggableElements.reduce { closest, child ->
            val box = child.getBoundingClientRect()
            val offset = y - box.top - box.height / 2
            if (offset < 0 && offset > Int.MIN_VALUE) {
                child
            } else {
                closest
            }
        }
    }

    val droppableStore = object : RootStore<Boolean>(false) {
        val dragOver: Handler<Unit> = handle { _, _ ->
            val event = dragovers.events.first()
            updatedDragAndDrop()
            afterElement = getDragAfterElement(droppable!!, event.clientY)
            event.preventDefault()
            true
        }
        val dragEnter: Handler<Unit> = handle { _, _ ->
            updatedDragAndDrop()
            this@droppable.attr("dragStatus", "hover")
            dragenters.events.first().preventDefault()
            true
        }
        val dragLeave: Handler<Unit> = handle { _, _ ->
            updatedDragAndDrop()
            this@droppable.attr("dragStatus", "empty")
            false
        }
        val drop: Handler<Unit> = handle { _, _ ->
            updatedDragAndDrop()
            this@droppable.attr("dragStatus", "empty")
            if (afterElement == null) {
                droppable?.append(draggable)
            } else {
                droppable?.insertBefore(draggable!!, afterElement)
            }

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




