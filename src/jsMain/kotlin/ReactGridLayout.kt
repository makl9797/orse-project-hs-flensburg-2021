@file:JsModule("react-grid-layout/build/ReactGridLayout")
@file:JsNonModule

import react.RClass
import react.dom.WithClassName

@JsName("default")
external val GridLayout: RClass<GridLayoutProps>

external interface GridLayoutProps : WithClassName {
    var layout: Array<GridItemProperties>
    var cols: Int
    var rowHeight: Int
    var width: Int
}

