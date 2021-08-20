package components.content

import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.app.AppState
import models.app.Module
import modules.calendar.calendar
import modules.info.infoBox
import modules.table.table
import stores.app.ModuleStore


@ExperimentalCoroutinesApi
fun RenderContext.moduleContainer(mode: AppState.Mode) {
    ModuleStore.data.renderEach { module ->
        moduleWrapper(
            style = {},
            id = module.id,
            mode = mode,
            module = module
        ) { style ->
            when (module.type) {
                Module.Type.INFOBOX -> {
                    infoBox(module.id, style)
                }
                Module.Type.TABLE -> {
                    table(module.id, style)
                }
                Module.Type.CALENDAR -> {
                    calendar(module.id, style)
                }
            }
        }
    }
}