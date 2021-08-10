package components.navigation.moduleCollection.moduleCard

import dev.fritz2.binding.SimpleHandler
import dev.fritz2.components.clickButton
import dev.fritz2.components.gridBox
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.params.AreaName
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.ModuleCard

@ExperimentalCoroutinesApi
fun RenderContext.moduleCollectionCard(card: ModuleCard, modalClose: SimpleHandler<Unit>) {
    val grid = object {
        val IMAGE: AreaName = "image"
        val NAME: AreaName = "name"
        val DESCRIPTION: AreaName = "description"
        val SETTINGS: AreaName = "settings"
        val BUTTON: AreaName = "button"
    }

    gridBox({
        height { "10rem" }
        minWidth { "31rem" }
        width { "100%" }
        columns { "10rem auto auto 7.5rem" }
        alignItems { center }
        border {
            width { "0.1rem" }
        }
        radius { "1rem" }
        margins { vertical { "0.4rem" } }
        paddings {
            left { "0.3rem" }
            right { "1rem" }
        }

        areas(
            sm = {
                with(grid) {
                    row(IMAGE, NAME, NAME, NAME)
                    row(IMAGE, DESCRIPTION, DESCRIPTION, DESCRIPTION)
                    row(IMAGE, DESCRIPTION, DESCRIPTION, DESCRIPTION)
                    row(IMAGE, SETTINGS, SETTINGS, BUTTON)
                }
            }
        )
    }, id = "${card.id}Card") {
        moduleCardImage(
            {
                grid {
                    area { grid.IMAGE }
                }
            },
            card.id,
            card.exampleImageSrc
        )
        moduleCardName(
            {
                grid {
                    area { grid.NAME }
                }
            },
            card.id,
            card.moduleName
        )
        moduleCardDescription(
            {
                grid {
                    area { grid.DESCRIPTION }
                }
            },
            card.id,
            card.moduleDescription
        )
        moduleCardSettings(
            {
                grid {
                    area { grid.SETTINGS }
                }
            },
            card.id,
            card.defaultSettings
        )
        clickButton({
            grid {
                area { grid.BUTTON }
            }
        }) {
            text("Hinzufügen")
            size { small }
            variant { outline }
        }
    }
}