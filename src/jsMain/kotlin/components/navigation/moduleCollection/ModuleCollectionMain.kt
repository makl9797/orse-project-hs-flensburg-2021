package components.navigation.moduleCollection

import components.navigation.modalTitle
import dev.fritz2.binding.SimpleHandler
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.Module
import models.store.ModuleCard
import models.store.ModuleSettings

@ExperimentalCoroutinesApi
fun RenderContext.moduleCollectionMain(id: String, modalClose: SimpleHandler<Unit>) {
    val exampleCard = ModuleCard(
        "Beispiel-Modul",
        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et",
        "https://via.placeholder.com/150?text=Module+Example+PicPlaceholder",
    )
    val exampleModule = Module(
        id = "exampleModule",
        type = Module.Type.INFOBOX,
        settings = ModuleSettings(),
        card = exampleCard
    )
    val testList = listOf(exampleModule, exampleModule, exampleModule)

    div(id = id) {
        modalTitle("moduleCollectionTitle", "Module")
        moduleCollection("moduleCollection", testList, modalClose)
        moduleCollectionFooter("moduleCollectionFooter", modalClose)
    }
}