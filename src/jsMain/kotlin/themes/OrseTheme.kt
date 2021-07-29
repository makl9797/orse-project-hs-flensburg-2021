package themes

import dev.fritz2.styling.theme.ColorScheme
import dev.fritz2.styling.theme.Colors
import dev.fritz2.styling.theme.DefaultTheme

object OrseTheme : DefaultTheme() {
    override val name = "Orse-Main-Theme"

    override val colors: Colors = object : Colors by super.colors {
        override val primary: ColorScheme = ColorScheme(
            main = "#8ECEFF",
            mainContrast = "#E6F4F1",
            highlight = "#00A0FF",
            highlightContrast = "#FCFCD4"
        )

        override val secondary: ColorScheme = ColorScheme(
            main = "#324757",
            mainContrast = "#F1F1E6",
            highlight = "#00BCFF",
            highlightContrast = "#FFF7D6"
        )
    }
}