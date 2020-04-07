package io.aiico.cells

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.aiico.cells.adapter.CardVerticalSpacing
import io.aiico.cells.adapter.Cell
import io.aiico.cells.adapter.CellAdapter
import io.aiico.cells.adapter.CellPresenter
import io.aiico.cells.adapter.CellsView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CellsView {

    private val presenter = CellPresenter()
    private val cellAdapter = CellAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initCellsRecyclerView()
        initCreationButton()
        presenter.onAttach(savedInstanceState, this)
    }

    private fun initCellsRecyclerView() {
        cellsRecyclerView.adapter = cellAdapter
        cellsRecyclerView.addItemDecoration(CardVerticalSpacing(this))
    }

    private fun initCreationButton() {
        createCellButton.setOnClickListener {
            presenter.onCreateCellClick()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onDetach(outState, cellAdapter.items)
    }

    override fun addCell(cell: Cell) {
        cellAdapter.addCell(cell)
        cellsRecyclerView.smoothScrollToPosition(cellAdapter.itemCount - 1)
    }

    override fun removeCellAt(position: Int) {
        cellAdapter.removeAt(position)
    }

    override fun showCells(cells: List<Cell>) {
        cellAdapter.showCells(cells)
    }
}
