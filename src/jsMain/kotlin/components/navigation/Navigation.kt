package components.navigation

import dev.fritz2.components.navBar
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.AppState

@ExperimentalCoroutinesApi
fun RenderContext.navigation(mode: AppState.Mode) {

    navBar({
        border { width { "0" } }
        boxShadow { flat }
    }) {
        brand {
            h1 { +"Orse" }
        }
        actions {
            when (mode) {
                AppState.Mode.WORK -> {
                    navigationMenu()
                }

                AppState.Mode.EDIT -> {
                    navigationEditMenu()

                }

                else -> {

                }
            }

        }
    }
}