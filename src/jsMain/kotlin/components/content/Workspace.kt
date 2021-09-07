package components.content

import dev.fritz2.components.gridBox
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.app.AppState

/**
 * Workspace-Component
 *
 * Defines the screen-space where the user can work with the modules.
 * Similar to a div as a grid.
 *
 * @param id defines the id of the html-element
 * @param mode defines the Mode between Work & Edit
 * @return Returns a grid-div
 */
@ExperimentalCoroutinesApi
fun RenderContext.workspace(id: String, mode: AppState.Mode) {
    gridBox({
        grid {
            row {
                start { "2" }
                end { "3" }
            }
        }
        columns { repeat(autoFill) { "1rem" } }
        rows { repeat(autoFill) { "1rem" } }
        height { "100%" }
    }, id = id) {
        moduleContainer(mode)
    }
}