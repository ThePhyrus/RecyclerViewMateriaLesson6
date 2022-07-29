package roman.bannikov.recyclerviewmaterialesson6

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(private val itemTouchHelperAdapter: ItemTouchHelperAdapter) :
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
        itemTouchHelperAdapter.onItemMove(sourse.adapterPosition, target.adapterPosition)
        return true //когда не знаешь о чём речь - пиши тру)) Андрей Нестеренко (преподаватель GB).
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        itemTouchHelperAdapter.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder is RecyclerViewAdapter.MarsViewHolder) {
                (viewHolder as RecyclerViewAdapter.MarsViewHolder).onItemSelected()
            super.onSelectedChanged(viewHolder, actionState)
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        if  (viewHolder is RecyclerViewAdapter.MarsViewHolder)
        (viewHolder as RecyclerViewAdapter.MarsViewHolder).onItemReleased()
        super.clearView(recyclerView, viewHolder)
    }
}