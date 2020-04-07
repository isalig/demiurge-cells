package io.aiico.cells.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.aiico.cells.R

class CellAdapter : RecyclerView.Adapter<CellViewHolder>() {

    private val cells = ArrayList<Cell>()

    val items: List<Cell> = cells

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CellViewHolder(inflateViewHolderView(parent))

    private fun inflateViewHolderView(parent: ViewGroup) =
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_cell, parent, false)

    override fun getItemCount() = cells.size

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        holder.bind(cells[position])
    }

    fun addCell(cell: Cell) {
        cells.add(cell)
        notifyItemInserted(cells.lastIndex)
    }

    fun removeAt(position: Int) {
        if (position < 0 || position > cells.lastIndex) {
            throw IndexOutOfBoundsException("Illegal position for removing. Position $position, cells count $itemCount")
        }

        cells.removeAt(position)
        notifyItemRemoved(position)
    }

    fun showCells(cells: List<Cell>) {
        with(this.cells) {
            clear()
            addAll(cells)
        }
        notifyDataSetChanged()
    }
}