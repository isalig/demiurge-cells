package io.aiico.cells.adapter

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import io.aiico.cells.R

enum class Cell(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val iconRes: Int,
    @DrawableRes val iconBackgroundRes: Int
) {

    DEAD_CELL(
        R.string.dead_cell_title,
        R.string.dead_cell_description,
        R.drawable.ic_dead_cell,
        R.drawable.dead_cell_icon_background
    ),

    ALIVE_CELL(
        R.string.alive_cell_title,
        R.string.alive_cell_description,
        R.drawable.ic_alive_cell,
        R.drawable.alive_cell_icon_background
    ),

    CREATURE(
        R.string.creature_title,
        R.string.creature_description,
        R.drawable.ic_creature,
        R.drawable.creature_icon_background
    )
}