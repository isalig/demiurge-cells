package io.aiico.cells.adapter

import android.os.Bundle
import kotlin.random.Random


class CellPresenter {

    private var view: CellsView? = null

    private var lastCreaturePosition = -1
    private var cellsGroupType: Cell? = null
    private var cellsGroupSize = 0
    private var cellsCount = 0

    fun onAttach(savedInstanceState: Bundle?, view: CellsView) {
        this.view = view
        savedInstanceState?.run {
            lastCreaturePosition = getInt(KEY_CREATURE_POSITION)
            cellsGroupSize = getInt(KEY_CELLS_GROUP_SIZE, 0)
            (getSerializable(KEY_CELL_GROUP_TYPE) as? Cell)?.let { type -> cellsGroupType = type }
            getStringArray(KEY_CELLS_LIST)?.let { cellNames ->
                val cells = cellNames.map { cellName -> Cell.valueOf(cellName) }
                cellsCount = cells.size
                view.showCells(cells)
            }
        }
    }

    fun onDetach(outState: Bundle, cells: List<Cell>) {
        view = null
        val cellNames = cells
            .map { cell -> cell.name }
            .toTypedArray()

        with(outState) {
            putInt(KEY_CREATURE_POSITION, lastCreaturePosition)
            putInt(KEY_CELLS_GROUP_SIZE, cellsGroupSize)
            putSerializable(KEY_CELL_GROUP_TYPE, cellsGroupType)
            putStringArray(KEY_CELLS_LIST, cellNames)
        }
    }

    fun onCreateCellClick() {
        view ?: return

        with(createCell()) {
            updateCellsGroup(this)
            addCell(this)
            if (isCreatureMustBorn()) {
                addCell(Cell.CREATURE)
                lastCreaturePosition = cellsCount - 1
                cellsGroupSize = 0
            }
            if (isCreatureMustDie()) {
                removeCellAt(lastCreaturePosition)
                lastCreaturePosition = -1
                cellsGroupSize = 0
            }
        }
    }

    private fun addCell(cell: Cell) {
        view?.addCell(cell)
        cellsCount++
    }

    private fun removeCellAt(position: Int) {
        view?.removeCellAt(position)
        cellsCount--
    }

    private fun createCell(): Cell {
//        uncomment to check death case
//        if (lastCreaturePosition > -1) {
//            return Cell.DEAD_CELL
//        }
        return when (Random.nextInt(2)) {
            0 -> Cell.DEAD_CELL
            else -> Cell.ALIVE_CELL
        }
    }

    private fun updateCellsGroup(cell: Cell) {
        if (cellsGroupType != cell) {
            cellsGroupSize = 0
            cellsGroupType = cell
        }

        cellsGroupSize++
    }

    private fun isCreatureMustDie() =
        cellsGroupType == Cell.DEAD_CELL &&
                cellsGroupSize >= CELLS_GROUP_CAPACITY &&
                lastCreaturePosition != -1 &&
                isCreatureNearCellsGroup()

    private fun isCreatureNearCellsGroup() =
        cellsCount - 1 - lastCreaturePosition == CELLS_GROUP_CAPACITY

    private fun isCreatureMustBorn() =
        cellsGroupType == Cell.ALIVE_CELL && cellsGroupSize >= CELLS_GROUP_CAPACITY

    companion object {
        private const val CELLS_GROUP_CAPACITY = 3
        private const val KEY_CREATURE_POSITION = "creature_position"
        private const val KEY_CELLS_LIST = "cells_list"
        private const val KEY_CELLS_GROUP_SIZE = "cells_group_size"
        private const val KEY_CELL_GROUP_TYPE = "cell_group_type"
    }
}