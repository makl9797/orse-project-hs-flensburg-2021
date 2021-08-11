package components.navigation.moduleCollection

import components.navigation.modalTitle
import dev.fritz2.binding.SimpleHandler
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import modules.ModuleCatalog

@ExperimentalCoroutinesApi
fun RenderContext.moduleCollectionMain(id: String, moduleCatalog: ModuleCatalog, modalClose: SimpleHandler<Unit>) {

    div(id = id) {
        modalTitle("moduleCollectionTitle", "Module")
        moduleCollection("moduleCollection", moduleCatalog.defaultModules, modalClose)
        moduleCollectionFooter("moduleCollectionFooter", modalClose)
    }
}