package modules.wrapper

import dev.fritz2.binding.Handler
import dev.fritz2.binding.RootStore
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.module(id: String, title: String, content: Div.() -> Unit): Div {


    return div({
        minWidth { "max-content" }
        minHeight { "20vh" }
        border {
            width { thin }
            color { secondary.main }
        }
        flex {
            grow { "1" }
        }
        overflowY { auto }
        overflowX { hidden }
        css("resize: both")

    }, id = id) {

        val isDragged = object : RootStore<Boolean>(false) {
            val dragStart: Handler<Unit> = handle { _, _ ->
                this@div.attr("isDragged", "true")
                true
            }

            val dragEnd: Handler<Unit> = handle { _, _ ->
                this@div.attr("isDragged", "false")
                false
            }

        }


        div({
            height { "1.5em" }
            width { "100%" }
            fontSize { "1em" }
            css("margin-left: 1em")
            css("cursor: move")
        }, id = (id + "TitleBar")) {
            +title
        }
        content()

        attr("draggable", "true")
        attr("isDragged", "false")

        dragstarts handledBy isDragged.dragStart
        dragends handledBy isDragged.dragEnd
    }
}
