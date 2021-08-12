package components.content

import dev.fritz2.components.gridBox
import dev.fritz2.components.icon
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.params.AreaName
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun RenderContext.moduleMoveController(
    id: String,
) {
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
        }) { fromTheme { circleAdd } }
        icon({
            grid {
                area { grid.INCREASERIGHT }
            }
        }) { fromTheme { circleAdd } }
        icon({
            grid {
                area { grid.INCREASEBOTTOM }
            }
        }) { fromTheme { circleAdd } }
        icon({
            grid {
                area { grid.INCREASELEFT }
            }
        }) { fromTheme { circleAdd } }
        icon({
            grid {
                area { grid.MOVETOPLEFT }
            }
        }) { fromTheme { arrowLeftUp } }
        icon({
            grid {
                area { grid.MOVETOP }
            }
        }) { fromTheme { arrowUp } }
        icon({
            grid {
                area { grid.MOVETOPRIGHT }
            }
        }) { fromTheme { arrowRightUp } }
        icon({
            grid {
                area { grid.MOVELEFT }
            }
        }) { fromTheme { arrowLeft } }
        icon({
            grid {
                area { grid.MOVERIGHT }
            }
        }) { fromTheme { arrowRight } }
        icon({
            grid {
                area { grid.MOVEBOTTOMLEFT }
            }
        }) { fromTheme { arrowLeftDown } }
        icon({
            grid {
                area { grid.MOVEBOTTOM }
            }
        }) { fromTheme { arrowDown } }
        icon({
            grid {
                area { grid.MOVEBOTTOMRIGHT }
            }
        }) { fromTheme { arrowRightDown } }


    }
}