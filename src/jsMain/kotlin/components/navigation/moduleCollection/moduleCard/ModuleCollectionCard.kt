package components.navigation.moduleCollection.moduleCard

import dev.fritz2.binding.SimpleHandler
import dev.fritz2.binding.SubStore
import dev.fritz2.components.clickButton
import dev.fritz2.components.gridBox
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.params.AreaName
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.L
import models.store.Module

@ExperimentalCoroutinesApi
fun RenderContext.moduleCollectionCard(
    module: SubStore<List<Module>, List<Module>, Module>,
    modalClose: SimpleHandler<Unit>
): Div {
    val moduleSettingsStore = module.sub(L.Module.settings)


    val grid = object {
        val IMAGE: AreaName = "image"
        val NAME: AreaName = "name"
        val DESCRIPTION: AreaName = "description"
        val SETTINGS: AreaName = "settings"
        val BUTTON: AreaName = "button"
    }

    return gridBox({
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
    }, id = "${module.id}Card") {
        moduleCardImage(
            {
                grid {
                    area { grid.IMAGE }
                }
            },
            module.current.id,
            module.current.card.exampleImageSrc
        )
        moduleCardName(
            {
                grid {
                    area { grid.NAME }
                }
            },
            module.current.id,
            module.current.card.moduleName
        )
        moduleCardDescription(
            {
                grid {
                    area { grid.DESCRIPTION }
                }
            },
            module.current.id,
            module.current.card.moduleDescription
        )
        moduleCardSettings(
            {
                grid {
                    area { grid.SETTINGS }
                }
            },
            module.current.id,
            moduleSettingsStore
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