package io.aiico.cells.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_cell.*

class CellViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(cell: Cell) {
        with(cell) {
            cellImageView.setImageResource(iconRes)
            cellImageView.setBackgroundResource(iconBackgroundRes)
            cellTitleTextView.setText(titleRes)
            cellDescriptionTextView.setText(descriptionRes)
        }
    }
}