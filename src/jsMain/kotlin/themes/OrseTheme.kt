package themes

import dev.fritz2.styling.theme.ColorScheme
import dev.fritz2.styling.theme.Colors
import dev.fritz2.styling.theme.DefaultTheme

object OrseTheme : DefaultTheme() {
    override val name = "Orse-Main-Theme"

    override val colors: Colors = object : Colors by super.colors {
        override val primary: ColorScheme = ColorScheme(
            main = "#093F75",
            mainContrast = "#5DA9F5",
            highlight = "#5EAAF7",
            highlightContrast = "#2E5378"
        )

        override val secondary: ColorScheme = ColorScheme(
            main = "#00a5cf",
            mainContrast = "#142F36",
            highlight = "#00BCFF",
            highlightContrast = "#FFF7D6"
        )
    }
}