package modules

import kotlinx.css.*
import react.*
import react.dom.h3
import styled.css
import styled.styledDiv

external interface ExampleProps : RProps {
    var exampleText: String
}

external interface ExampleState : RState {
    var exampleState: String
}


class Example : RComponent<ExampleProps, ExampleState>() {
    override fun ExampleState.init() {
        exampleState = "State 1"
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                display = Display.flex
                borderStyle = BorderStyle.solid
                borderWidth = 3.px
                width = 200.px
                height = 200.px
                justifyContent = JustifyContent.center
                backgroundColor = Color("#B6D1DE")
            }
            h3 {
                +props.exampleText
            }
            h3 {
                +state.exampleState
            }
        }
    }
}

@ExperimentalJsExport
fun RBuilder.example(handler: ExampleProps.() -> Unit): ReactElement {
    return child(Example::class) {
        this.attrs(handler)
    }
}