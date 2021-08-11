package components.content

import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.AppState
import models.store.Module
import modules.info.infoBox
import stores.moduleStore


@ExperimentalCoroutinesApi
fun RenderContext.moduleContainer(mode: AppState.Mode) {
    moduleStore.data.renderEach { module ->
        console.log(module)
        moduleWrapper(
            style = {},
            id = module.id,
            title = module.card.moduleName,
            settings = module.settings,
            mode = mode
        ) { style ->
            when (module.type) {
                Module.Type.INFOBOX -> {
                    infoBox(module.id, style)
                }
                Module.Type.TABLE -> {

                }
                Module.Type.CALENDAR -> {

                }
                else -> {

                }
            }
        }
    }
}