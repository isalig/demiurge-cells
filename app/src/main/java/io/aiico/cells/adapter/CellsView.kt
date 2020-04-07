package io.aiico.cells.adapter

interface CellsView {

    fun addCell(cell: Cell)

    fun removeCellAt(position: Int)

    fun showCells(cells: List<Cell>)
}