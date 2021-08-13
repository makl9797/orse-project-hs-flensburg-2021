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
    val xStore = settingsStore.sub(L.ModuleSettings.startX)
    val yStore = settingsStore.sub(L.ModuleSettings.startY)

    val changeWidth = widthStore.handle { _, value: String ->
        value.toInt()
    }
    val changeHeight = heightStore.handle { _, value: String ->
        value.toInt()
    }
    val changeX = xStore.handle { _, value: String ->
        value.toInt()
    }
    val changeY = yStore.handle { _, value: String ->
        value.toInt()
    }

    flexBox({
        justifyContent { spaceBetween }
        alignItems { center }
        margins { horizontal { "0.5rem" } }
        style()
    }, id = "${id}Settings") {
        moduleCardSettingsInput(id = id, startValue = settings.width, "Weite", "W", changeWidth)
        moduleCardSettingsInput(id = id, startValue = settings.height, "Höhe", "H", changeHeight)
        moduleCardSettingsInput(id = id, startValue = settings.startX, "Position X", "X", changeX)
        moduleCardSettingsInput(id = id, startValue = settings.startY, "Position Y", "Y", changeY)
    }
}