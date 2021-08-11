package components.content

import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.AppState
import models.store.Module
import modules.info.infoModule
import stores.moduleStore

@ExperimentalCoroutinesApi
fun RenderContext.moduleContainer(mode: AppState.Mode) {
    moduleStore.data.renderEach { module ->
        moduleWrapper(
            {},
            id = module.id,
            module.card.moduleName,
            module.settings,
            mode
        ) { style ->
            when (module.type) {
                Module.Type.INFOBOX -> {
                    infoModule(module.id, style)
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