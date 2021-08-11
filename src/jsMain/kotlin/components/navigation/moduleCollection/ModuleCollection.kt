package components.navigation.moduleCollection

import components.navigation.moduleCollection.moduleCard.moduleCollectionCard
import dev.fritz2.binding.SimpleHandler
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.Module
import stores.moduleStore

@ExperimentalCoroutinesApi
fun RenderContext.moduleCollection(id: String, modules: List<Module>, modalClose: SimpleHandler<Unit>) {
    div({
        paddings { vertical { "1rem" } }
    }, id = id) {
        moduleStore.renderEach(Module::id) { module ->
            moduleCollectionCard(module, modalClose)
        }
    }
}