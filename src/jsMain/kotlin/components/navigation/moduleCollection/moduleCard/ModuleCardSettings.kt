package components.navigation.moduleCollection.moduleCard

import dev.fritz2.binding.SubStore
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.params.FlexParams
import dev.fritz2.styling.params.Style
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.L
import models.store.Module
import models.store.ModuleSettings


@ExperimentalCoroutinesApi
fun RenderContext.moduleCardSettings(
    style: Style<FlexParams>, id: String,
    settings: ModuleSettings,
    settingsStore: SubStore<Module, Module, ModuleSettings>
) {

    val widthStore = settingsStore.sub(L.ModuleSettings.width)
    val heightStore = settingsStore.sub(L.ModuleSettings.height)
    val xStore = settingsStore.sub(L.ModuleSettings.parentX)
    val yStore = settingsStore.sub(L.ModuleSettings.parentY)

    val changeWidth = widthStore.handle { _, value: String ->
        value.toDouble()
    }
    val changeHeight = heightStore.handle { _, value: String ->
        value.toDouble()
    }
    val changeX = xStore.handle { _, value: String ->
        value.toDouble()
    }
    val changeY = yStore.handle { _, value: String ->
        value.toDouble()
    }

    flexBox({
        justifyContent { spaceBetween }
        alignItems { center }
        margins { horizontal { "0.5rem" } }
        style()
    }, id = "${id}Settings") {
        moduleCardSettingsInput(id = id, startValue = settings.width, "Weite", "W", changeWidth)
        moduleCardSettingsInput(id = id, startValue = settings.height, "Höhe", "H", changeHeight)
        moduleCardSettingsInput(id = id, startValue = settings.parentX, "Position X", "X", changeX)
        moduleCardSettingsInput(id = id, startValue = settings.parentY, "Position Y", "Y", changeY)
    }
}