package io.aiico.cells.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.aiico.cells.R

class CardVerticalSpacing(context: Context) : RecyclerView.ItemDecoration() {

    private val verticalSpacing: Int = context.resources.getDimensionPixelSize(R.dimen.card_vertical_spacing)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = verticalSpacing
    }
}