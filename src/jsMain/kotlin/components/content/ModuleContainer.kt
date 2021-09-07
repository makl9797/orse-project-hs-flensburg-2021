package components.content

import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.app.AppState
import models.app.Module
import modules.calendar.calendar
import modules.info.infoBox
import modules.table.table
import stores.app.ModuleStore

/**
 * Modulcontainer-Component
 *
 * Contains all modules
 * Displays every defined module from the ModuleStore
 *
 * @param mode defines the Mode between Work & Edit
 * @return Returns and renders a list of modules
 */
@ExperimentalCoroutinesApi
fun RenderContext.moduleContainer(mode: AppState.Mode) {
    ModuleStore.data.renderEach { module ->
        moduleWrapper(
            style = {},
            id = module.id,
            mode = mode,
            module = module
        ) { style ->
            /**
             * Decides between the given module type and takes the described module from the modules folder
             * New Modules need to be added in the when block
             */
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