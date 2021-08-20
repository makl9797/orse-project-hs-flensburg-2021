package components.navigation.moduleCollection.moduleCard

import dev.fritz2.binding.SimpleHandler
import dev.fritz2.binding.storeOf
import dev.fritz2.components.gridBox
import dev.fritz2.components.pushButton
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.params.AreaName
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import models.app.L
import models.app.Module
import stores.app.ModuleStore

@ExperimentalCoroutinesApi
fun RenderContext.moduleCollectionCard(
    module: Module,
    modalClose: SimpleHandler<Unit>
): Div {
    val localStore = storeOf(module)
    val settingsStore = localStore.sub(L.Module.settings)

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
            module.id,
            module.card.exampleImageSrc,
            "${module.card.moduleName}_Pic"
        )
        moduleCardName(
            {
                grid {
                    area { grid.NAME }
                }
            },
            module.id,
            module.card.moduleName
        )
        moduleCardDescription(
            {
                grid {
                    area { grid.DESCRIPTION }
                }
            },
            module.id,
            module.card.moduleDescription
        )
        moduleCardSettings(
            {
                grid {
                    area { grid.SETTINGS }
                }
            },
            module.id,
            module.settings,
            settingsStore
        )
        pushButton({
            grid {
                area { grid.BUTTON }
            }
        }) {
            text("Hinzufügen")
            size { small }
            variant { outline }
            events {
                mousedowns.events.map { localStore.current } handledBy ModuleStore.addModule
                mouseups handledBy modalClose
            }
        }


    }
}