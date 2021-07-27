import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

@ExperimentalJsExport
class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
       moduleContainer {}
    }

}