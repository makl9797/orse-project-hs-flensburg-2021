package components.navigation

import dev.fritz2.components.navBar
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.styling.div
import kotlinx.coroutines.ExperimentalCoroutinesApi
import models.store.AppState

@ExperimentalCoroutinesApi
fun RenderContext.navigation(id: String, mode: AppState.Mode) {
    div({
        height { "4.5rem" }
    }, id = id) {
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
                        navigationMenu("navigationMenu")
                    }

                    AppState.Mode.EDIT -> {
                        navigationEditMenu("navigationEditMenu")

                    }

                    else -> {

                    }
                }

            }
        }
    }
}