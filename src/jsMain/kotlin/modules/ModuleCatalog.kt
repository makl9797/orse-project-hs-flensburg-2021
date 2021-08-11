package modules

import modules.info.InfoBox

class ModuleCatalog() {
    private val infoBox: InfoBox = InfoBox()

    val defaultModules = listOf(
        infoBox.createModule()
    )

}