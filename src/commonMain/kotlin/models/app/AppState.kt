package models.app

import dev.fritz2.lenses.Lenses

@Lenses
data class AppState(
    var mode: Mode = Mode.NONE
) {
    enum class Mode {
        WORK, EDIT, NONE
    }
}
