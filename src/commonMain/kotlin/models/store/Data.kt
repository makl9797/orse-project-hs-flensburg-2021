package models.store


import dev.fritz2.lenses.Lenses

@Lenses
data class Data(
    var data: String = ""
)
