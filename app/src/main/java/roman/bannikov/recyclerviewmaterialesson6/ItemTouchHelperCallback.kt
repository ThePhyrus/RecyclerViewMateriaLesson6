package roman.bannikov.recyclerviewmaterialesson6

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(private val adapter: RecyclerViewAdapter) :
    ItemTouchHelper.Callback() {

    override fun getMovementFlags( //тут описываются направления (куда тащить, куда свайпить)
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        sourse: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(sourse.adapterPosition,target.adapterPosition)
        return true //когда не знаешь о чём речь - пиши тру)) Андрей Нестеренко (преподаватель GB).
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemDismiss(viewHolder.adapterPosition)
    }

}