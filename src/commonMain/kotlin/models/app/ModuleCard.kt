package models.app

import dev.fritz2.lenses.Lenses

@Lenses
data class ModuleCard(
    val moduleName: String,
    val moduleDescription: String,
    val exampleImageSrc: String,
)
