package stores.models

data class AppState(
    var mode: Mode = Mode.NONE
) {
    enum class Mode {
        WORK, EDIT, NONE
    }
}
