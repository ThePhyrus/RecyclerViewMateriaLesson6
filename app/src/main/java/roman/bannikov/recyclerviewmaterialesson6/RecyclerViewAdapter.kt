package roman.bannikov.recyclerviewmaterialesson6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import roman.bannikov.recyclerviewmaterialesson6.databinding.*


//FIXME лучше бы использовать sealed-class вместо констант? Как это реализовать?
const val TYPE_EARTH = 1
const val TYPE_MARS = 2
const val TYPE_CARD = 3
const val TYPE_HEADER = 4


class RecyclerViewAdapter(
    private var onListItemClickListener: OnListItemClickListener
) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private lateinit var list: MutableList<Pair<Data, Boolean>>

    fun setList(newList: List<Pair<Data, Boolean>>) {
        this.list = newList.toMutableList()
    }

    fun setAddToList(newList: List<Pair<Data, Boolean>>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemChanged(position)
    }

    fun setRemoveToList(newList: List<Pair<Data, Boolean>>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemRemoved(position)
    }


    override fun getItemViewType(position: Int): Int {
        return list[position].first.type
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
        BaseViewHolder(view) {
        private var isMarsDescriptionHidden = true
        override fun bind(listItem: Pair<Data, Boolean>) {
            (ActivityRecyclerItemMarsBinding.bind(itemView)).apply {
                tvTitle.text = listItem.first.titleText
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
                    list[layoutPosition] = list[layoutPosition].let {
                        it.first to !it.second
                    }
                    tvMarsDescription.visibility =
                        if (list[layoutPosition].second) View.VISIBLE else View.GONE
//                    notifyItemChanged(layoutPosition)//FIXME криво анимируется не с первого клика
                }
            }
        }
    }


    class EarthViewHolder(view: View) :
        BaseViewHolder(view) {
        override fun bind(listItem: Pair<Data, Boolean>) {
            (ActivityRecyclerItemEarthBinding.bind(itemView)).apply {
                tvTitle.text = listItem.first.titleText
                tvDescription.text = listItem.first.description
            }
        }
    }

    class CardViewHolder(view: View) :
        BaseViewHolder(view) {
        override fun bind(listItem: Pair<Data, Boolean>) {
            (ActivityRecyclerItemCardBinding.bind(itemView)).apply {
                tvName.text = listItem.first.name
                tvLastName.text = listItem.first.lastName
            }
        }
    }

    class HeaderViewHolder(view: View) :
        BaseViewHolder(view) {
        override fun bind(listItem: Pair<Data, Boolean>) {
            (ActivityRecyclerItemHeaderBinding.bind(itemView)).apply {
                tvHeader.text = listItem.first.titleText
            }
        }
    }


}