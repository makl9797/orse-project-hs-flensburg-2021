package components.navigation.moduleCollection.moduleCard

import dev.fritz2.components.inputField
import dev.fritz2.components.tooltip
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.label
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.moduleCardSettingsInput(
    id: String,
    startValue: Double,
    valueName: String,
    valueShortCut: String
) {
    label({
        tooltip(valueName) { bottom }()
    }) { +"${valueShortCut}:" }
    inputField({
        maxWidth { "4rem" }
        paddings { horizontal { "0.25rem" } }
        height { "1.75rem" }
    }, id = id) {
        type("number")
        placeholder("$startValue")
        step("1")
    }
}