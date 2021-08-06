package components

import dev.fritz2.components.dropdown
import dev.fritz2.components.menu
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalJsExport
fun RenderContext.mainMenu(): Div {
    return div("menu", "mainMenu") {
        dropdown({
            background {
                color { primary.main }

            }
            color { secondary.main }
        }) {
            placement { bottom }
            alignment { end }
            content {
                menu {
                    entry {
                        icon { grid }
                        text("Module Anpassen")
                        events {
                            clicks handledBy appStore.switchMode
                        }
                    }
                    entry {
                        icon { settings }
                        text("Einstellungen")
                        events {

                        }
                    }
                }
            }
        }
    }
}


