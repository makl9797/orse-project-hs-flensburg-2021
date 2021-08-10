package components.navigation.moduleCollection

import components.navigation.modalTitle
import dev.fritz2.binding.SimpleHandler
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.ModuleCard
import models.store.ModuleSettings

@ExperimentalCoroutinesApi
fun RenderContext.moduleCollectionMain(id: String, modalClose: SimpleHandler<Unit>) {
    val exampleCard = ModuleCard(
        "exampleModule",
        "Beispiel-Modul",
        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et",
        "https://via.placeholder.com/150?text=Module+Example+PicPlaceholder",
        ModuleSettings(100.0, 100.0, 20.0, 0.0)
    )
    val testList = listOf(exampleCard, exampleCard, exampleCard)

    div(id = id) {
        modalTitle("moduleCollectionTitle", "Module")
        moduleCollection("moduleCollection", testList, modalClose)
        moduleCollectionFooter("moduleCollectionFooter", modalClose)
    }
}