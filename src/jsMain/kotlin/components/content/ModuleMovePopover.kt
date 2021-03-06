package components.content

import dev.fritz2.binding.SubStore
import dev.fritz2.components.gridBox
import dev.fritz2.components.icon
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.params.AreaName
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.app.Module
import models.app.ModuleSettings

/**
 * ModuleMovePopover-Component
 *
 * Popover-Window which contains button to adjust the size and position of the module.
 *
 * @param id defines the id of the popover element
 * @param settingsStore Store of the current module-settings
 * @return Returns a popover element
 */
@ExperimentalCoroutinesApi
fun RenderContext.moduleMoveController(
    id: String,
    settingsStore: SubStore<Module, Module, ModuleSettings>
) {
    val increaseHeight = settingsStore.handle { model ->
        ModuleSettings(model.width, model.height + 1, model.startX, model.startY)
    }
    val increaseWidth = settingsStore.handle { model ->
        ModuleSettings(model.width + 1, model.height, model.startX, model.startY)
    }
    val decreaseHeight = settingsStore.handle { model ->
        ModuleSettings(model.width, model.height - 1, model.startX, model.startY)
    }
    val decreaseWidth = settingsStore.handle { model ->
        ModuleSettings(model.width - 1, model.height, model.startX, model.startY)
    }
    val moveLeft = settingsStore.handle { model ->
        ModuleSettings(model.width - 1, model.height, model.startX - 1, model.startY)
    }
    val moveRight = settingsStore.handle { model ->
        ModuleSettings(model.width + 1, model.height, model.startX + 1, model.startY)
    }
    val moveUp = settingsStore.handle { model ->
        ModuleSettings(model.width, model.height - 1, model.startX, model.startY - 1)
    }
    val moveDown = settingsStore.handle { model ->
        ModuleSettings(model.width, model.height + 1, model.startX, model.startY + 1)
    }
    val moveRightUp = settingsStore.handle { model ->
        ModuleSettings(model.width + 1, model.height - 1, model.startX + 1, model.startY - 1)
    }
    val moveRightDown = settingsStore.handle { model ->
        ModuleSettings(model.width + 1, model.height + 1, model.startX + 1, model.startY + 1)
    }
    val moveLeftUp = settingsStore.handle { model ->
        ModuleSettings(model.width - 1, model.height - 1, model.startX - 1, model.startY - 1)
    }
    val moveLeftDown = settingsStore.handle { model ->
        ModuleSettings(model.width - 1, model.height + 1, model.startX - 1, model.startY + 1)
    }

    val grid = object {
        val INCREASETOP: AreaName = "addTop"
        val INCREASERIGHT: AreaName = "addRight"
        val INCREASEBOTTOM: AreaName = "addBottom"
        val INCREASELEFT: AreaName = "addLeft"

        val MOVETOP: AreaName = "moveTop"
        val MOVERIGHT: AreaName = "moveRight"
        val MOVELEFT: AreaName = "moveLeft"
        val MOVEBOTTOM: AreaName = "moveBottom"
        val MOVEBOTTOMRIGHT: AreaName = "moveBottomRight"
        val MOVEBOTTOMLEFT: AreaName = "moveBottomLeft"
        val MOVETOPRIGHT: AreaName = "moveTopRight"
        val MOVETOPLEFT: AreaName = "moveTopLeft"

        val NONETOPLEFT: AreaName = "NoneTopLeft"
        val NONETOPRIGHT: AreaName = "NoneTopRight"
        val NONEBOTTOMLEFT: AreaName = "NoneBottomLeft"
        val NONEBOTTOMRIGHT: AreaName = "NoneBottomRight"
        val NONEMID: AreaName = "NoneMid"
    }

    gridBox({
        justifyContent { center }
        alignItems { center }
        css("justify-items:center")
        columns { repeat(5) { "auto" } }
        areas(
            sm = {
                with(grid) {
                    row(NONETOPLEFT, INCREASETOP, INCREASETOP, INCREASETOP, NONETOPRIGHT)
                    row(INCREASELEFT, MOVETOPLEFT, MOVETOP, MOVETOPRIGHT, INCREASERIGHT)
                    row(INCREASELEFT, MOVELEFT, NONEMID, MOVERIGHT, INCREASERIGHT)
                    row(INCREASELEFT, MOVEBOTTOMLEFT, MOVEBOTTOM, MOVEBOTTOMRIGHT, INCREASERIGHT)
                    row(NONEBOTTOMLEFT, INCREASEBOTTOM, INCREASEBOTTOM, INCREASEBOTTOM, NONEBOTTOMRIGHT)
                }
            }

        )
    }, id = id + "MoveController") {
        icon({
            grid {
                area { grid.INCREASETOP }
            }
            hover {
                color { primary.highlight }
            }
        }) {
            fromTheme { circleRemove }
            events {
                clicks handledBy decreaseHeight
            }
        }
        icon({
            grid {
                area { grid.INCREASERIGHT }
            }
            hover {
                color { primary.highlight }
            }
        }) {
            fromTheme { circleAdd }
            events {
                clicks handledBy increaseWidth
            }
        }
        icon({
            grid {
                area { grid.INCREASEBOTTOM }
            }
            hover {
                color { primary.highlight }
            }
        }) {
            fromTheme { circleAdd }
            events {
                clicks handledBy increaseHeight
            }
        }
        icon({
            grid {
                area { grid.INCREASELEFT }
            }
            hover {
                color { primary.highlight }
            }
        }) {
            fromTheme { circleRemove }
            events {
                clicks handledBy decreaseWidth
            }
        }
        icon({
            grid {
                area { grid.MOVETOPLEFT }
            }
            hover {
                color { primary.highlight }
            }
        }) {
            fromTheme { arrowLeftUp }
            events {
                clicks handledBy moveLeftUp
            }
        }
        icon({
            grid {
                area { grid.MOVETOP }
            }
            hover {
                color { primary.highlight }
            }
        }) {
            fromTheme { arrowUp }
            events {
                clicks handledBy moveUp
            }
        }
        icon({
            grid {
                area { grid.MOVETOPRIGHT }
            }
            hover {
                color { primary.highlight }
            }
        }) {
            fromTheme { arrowRightUp }
            events {
                clicks handledBy moveRightUp
            }
        }
        icon({
            grid {
                area { grid.MOVELEFT }
            }
            hover {
                color { primary.highlight }
            }
        }) {
            fromTheme { arrowLeft }
            events {
                clicks handledBy moveLeft
            }
        }
        icon({
            grid {
                area { grid.MOVERIGHT }
            }
            hover {
                color { primary.highlight }
            }
        }) {
            fromTheme { arrowRight }
            events {
                clicks handledBy moveRight
            }
        }
        icon({
            grid {
                area { grid.MOVEBOTTOMLEFT }
            }
            hover {
                color { primary.highlight }
            }
        }) {
            fromTheme { arrowLeftDown }
            events {
                clicks handledBy moveLeftDown
            }
        }
        icon({
            grid {
                area { grid.MOVEBOTTOM }
            }
            hover {
                color { primary.highlight }
            }
        }) {
            fromTheme { arrowDown }
            events {
                clicks handledBy moveDown
            }
        }
        icon({
            grid {
                area { grid.MOVEBOTTOMRIGHT }
            }
            hover {
                color { primary.highlight }
            }
        }) {
            fromTheme { arrowRightDown }
            events {
                clicks handledBy moveRightDown
            }
        }


    }
}