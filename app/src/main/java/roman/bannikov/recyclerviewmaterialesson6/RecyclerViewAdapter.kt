package roman.bannikov.recyclerviewmaterialesson6

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import roman.bannikov.recyclerviewmaterialesson6.databinding.ActivityRecyclerItemCardBinding
import roman.bannikov.recyclerviewmaterialesson6.databinding.ActivityRecyclerItemEarthBinding
import roman.bannikov.recyclerviewmaterialesson6.databinding.ActivityRecyclerItemHeaderBinding
import roman.bannikov.recyclerviewmaterialesson6.databinding.ActivityRecyclerItemMarsBinding


//FIXME лучше бы использовать sealed-class вместо констант? Как это реализовать?
const val TYPE_EARTH = 1
const val TYPE_MARS = 2
const val TYPE_CARD = 3
const val TYPE_HEADER = 4


class RecyclerViewAdapter(
    private var onListItemClickListener: OnListItemClickListener
) :
    RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    private lateinit var list: MutableList<Data>

    fun setList(newList: MutableList<Data>) {
        this.list = newList.toMutableList()
    }

    fun setAddToList(newList: MutableList<Data>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemChanged(position)
    }

    fun setRemoveToList(newList: MutableList<Data>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemRemoved(position)
    }


    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            TYPE_EARTH -> {
                val view =
                    ActivityRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context))
                return EarthViewHolder(view.root)
            }
            TYPE_MARS -> {
                val view =
                    ActivityRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                return MarsViewHolder(view.root)
            }
            TYPE_CARD -> {
                val view =
                    ActivityRecyclerItemCardBinding.inflate(LayoutInflater.from(parent.context))
                return CardViewHolder(view.root)
            }
            TYPE_HEADER -> {
                val view =
                    ActivityRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                return HeaderViewHolder(view.root)
            }

            else -> {
                val view =
                    ActivityRecyclerItemCardBinding.inflate(LayoutInflater.from(parent.context))
                return CardViewHolder(view.root)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class MarsViewHolder(view: View) :
        BaseViewHolder(view), ItemTouchHelperViewHolder {
        private var isMarsDescriptionHidden = true
        override fun bind(data: Data) {
            (ActivityRecyclerItemMarsBinding.bind(itemView)).apply {
                tvTitle.text = data.titleText
                btnAddItem.setOnClickListener {
                    onListItemClickListener.onAddBtnClick(layoutPosition)
                }
                btnRemoveItem.setOnClickListener {
                    onListItemClickListener.onRemoveBtnClick(layoutPosition)
                }
                btnMoveUp.setOnClickListener {
                    if (layoutPosition > 1) {
                        list.removeAt(layoutPosition).apply {
                            list.add(layoutPosition - 1, this)
                        }
                        notifyItemMoved(layoutPosition, layoutPosition - 1)
                    }
                }
                btnMoveDown.setOnClickListener {
                    if (layoutPosition + 1 in list.indices) {
                        list.removeAt(layoutPosition).apply {
                            list.add(layoutPosition + 1, this)
                        }
                        notifyItemMoved(layoutPosition, layoutPosition + 1)
                    }
                }
                ivMars.setOnClickListener {
                    if (isMarsDescriptionHidden){
                        tvMarsDescription.visibility = View.VISIBLE
//                        notifyItemChanged(layoutPosition)
                    } else {
                        tvMarsDescription.visibility = View.GONE
//                        notifyItemChanged(layoutPosition)
                    }
                    isMarsDescriptionHidden = !isMarsDescriptionHidden
                }
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemReleased() {
            itemView.setBackgroundColor(0)
        }
    }


    class EarthViewHolder(view: View) :
        BaseViewHolder(view) {
        override fun bind(data: Data) {
            (ActivityRecyclerItemEarthBinding.bind(itemView)).apply {
                tvTitle.text = data.titleText
                tvDescription.text = data.description
            }
        }
    }

    class CardViewHolder(view: View) :
        BaseViewHolder(view) {
        override fun bind(data: Data) {
            (ActivityRecyclerItemCardBinding.bind(itemView)).apply {
                tvName.text = data.name
                tvLastName.text = data.lastName
            }
        }
    }

    class HeaderViewHolder(view: View) :
        BaseViewHolder(view) {
        override fun bind(data: Data) {
            (ActivityRecyclerItemHeaderBinding.bind(itemView)).apply {
                tvHeader.text = data.titleText
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (toPosition > 0){
            list.removeAt(fromPosition).apply {
                list.add(toPosition, this)
            }
            notifyItemMoved(fromPosition, toPosition)
        }
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position) //Лучше (правильней) это делать не внутри адаптера!!!
        notifyItemRemoved(position)
    }


}