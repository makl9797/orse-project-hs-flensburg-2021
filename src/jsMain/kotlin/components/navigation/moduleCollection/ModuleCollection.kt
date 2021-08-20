package components.navigation.moduleCollection

import components.navigation.moduleCollection.moduleCard.moduleCollectionCard
import dev.fritz2.binding.SimpleHandler
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.app.Module

@ExperimentalCoroutinesApi
fun RenderContext.moduleCollection(id: String, moduleExamples: List<Module>, modalClose: SimpleHandler<Unit>) {
    div({
        paddings { vertical { "1rem" } }
    }, id = id) {
        moduleExamples.forEach { module ->
            moduleCollectionCard(module, modalClose)
        }
    }
}