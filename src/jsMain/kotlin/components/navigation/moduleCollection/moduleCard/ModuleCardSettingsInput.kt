package components.navigation.moduleCollection.moduleCard

import dev.fritz2.binding.SimpleHandler
import dev.fritz2.components.inputField
import dev.fritz2.components.tooltip
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.values
import dev.fritz2.styling.label
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.moduleCardSettingsInput(
    id: String,
    startValue: Int,
    valueName: String,
    valueShortCut: String,
    handler: SimpleHandler<String>
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
        events {
            changes.values() handledBy handler
        }
    }
}