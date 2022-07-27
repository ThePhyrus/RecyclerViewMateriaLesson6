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
    private var list: List<Data>
) :
    RecyclerView.Adapter<BaseViewHolder>() {

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




    class MarsViewHolder(view: View) :
        BaseViewHolder(view) {
        override fun bind(data: Data) {
            (ActivityRecyclerItemMarsBinding.bind(itemView)).apply {
                tvTitle.text = data.titleText


            }
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


}