import kotlinx.css.*
import react.*
import react.dom.*
import styled.css
import styled.styledDiv

external interface ExampleProps : RProps {
    var gridKey: String
    var className: String
}

@ExperimentalJsExport
val Module: RClass<ExampleProps> = forwardRef { p, RRef ->
    div {
        key = p.gridKey
        ref = RRef
        attrs["className"] = p.className
    }
}