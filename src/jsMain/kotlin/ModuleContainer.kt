import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.css.*
import org.w3c.dom.Element
import react.*
import react.dom.div
import react.dom.findDOMNode
import react.dom.p
import styled.css
import styled.styledDiv
import kotlin.coroutines.CoroutineContext


@ExperimentalJsExport
class ModuleContainer : RComponent<RProps, ModuleContainerState>() {
    override fun ModuleContainerState.init() {
        gridRef = createRef<RClass<GridLayoutProps>>()
        gridLayout = arrayOf(
            GridItemProperties("a", 0, 0, 1, 1),
            GridItemProperties("b", 1, 0, 1, 1),
            GridItemProperties("c", 2, 0, 1, 1)
        )
    }


    override fun RBuilder.render() {
        styledDiv {
            css {
                backgroundColor = Color.blueViolet
            }
            GridLayout {
                ref {
                    state.gridRef = it as RRef
                }
                attrs {
                    width = 1500
                    layout = state.gridLayout
                    cols = 5
                    rowHeight = 300
                }
                Module {
                    ref = state.gridRef
                    attrs {
                        gridKey = "a"
                    }
                    styledDiv {
                        css {
                            display = Display.flex
                            borderStyle = BorderStyle.solid
                            borderWidth = 5.px
                            width = 200.px
                            height = 200.px
                            justifyContent = JustifyContent.center
                            backgroundColor = Color.aliceBlue
                        }
                        p {
                            +"Module"
                        }
                    }
                }
                styledDiv {
                    key = "b"
                    css {
                        display = Display.flex
                        borderStyle = BorderStyle.solid
                        borderWidth = 5.px
                        width = 200.px
                        height = 200.px
                        justifyContent = JustifyContent.center
                        backgroundColor = Color.aliceBlue
                    }
                    p {
                        +"No Module"
                    }
                }
            }
        }
    }
}

external interface ModuleContainerState : RState {
    var gridLayout: Array<GridItemProperties>
    var gridRef: RRef
}

@ExperimentalJsExport
fun RBuilder.moduleContainer(handler: RProps.() -> Unit): ReactElement {
    return child(ModuleContainer::class) {
        this.attrs(handler)
    }
}


data class GridItemProperties(
    var i: String,
    var x: Int,
    var y: Int,
    var w: Int,
    var h: Int,
    var minW: Int? = 0,
    var maxW: Int? = Int.MAX_VALUE,
    var minH: Int? = 0,
    var maxH: Int? = Int.MAX_VALUE,
    var static: Boolean? = false,
    var isDraggable: Boolean? = true,
    var isResizable: Boolean? = true,
    var isBounded: Boolean? = false
)

