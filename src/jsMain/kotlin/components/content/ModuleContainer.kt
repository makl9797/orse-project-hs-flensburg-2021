package components.content

import dev.fritz2.dom.html.RenderContext
import kotlinx.browser.document
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.AppState
import models.store.Module
import modules.info.infoBox
import org.w3c.dom.Element
import stores.MoveModuleStore
import stores.moduleStore

val moveModuleStore = MoveModuleStore(document.body as Element)

@ExperimentalCoroutinesApi
fun RenderContext.moduleContainer(mode: AppState.Mode) {
    moduleStore.renderEach { module ->
        moduleWrapper(
            style = {},
            id = module.current.id,
            mode = mode,
            subStore = module
        ) { style ->
            when (module.current.type) {
                Module.Type.INFOBOX -> {
                    infoBox(module.current.id, style)
                }
                Module.Type.TABLE -> {

                }
                Module.Type.CALENDAR -> {

                }
//                else -> {
//
//                }
            }
        }
    }
}