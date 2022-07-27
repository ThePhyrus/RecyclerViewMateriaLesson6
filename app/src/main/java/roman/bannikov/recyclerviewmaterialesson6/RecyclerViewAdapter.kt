package roman.bannikov.recyclerviewmaterialesson6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import roman.bannikov.recyclerviewmaterialesson6.databinding.ActivityRecyclerItemCardBinding
import roman.bannikov.recyclerviewmaterialesson6.databinding.ActivityRecyclerItemEarthBinding
import roman.bannikov.recyclerviewmaterialesson6.databinding.ActivityRecyclerItemMarsBinding


//FIXME лучше бы использовать sealed-class вместо констант? Как это реализовать?
const val TYPE_EARTH = 1
const val TYPE_MARS = 2
const val TYPE_CARD = 3


class RecyclerViewAdapter(private var list: List<Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
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

            else -> {
                val view =
                    ActivityRecyclerItemCardBinding.inflate(LayoutInflater.from(parent.context))
                return CardViewHolder(view.root)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) { //todo создать BaseViewHolder
            TYPE_EARTH -> {
                (holder as EarthViewHolder).myBind(list[position])
            }
            TYPE_MARS -> {
                (holder as MarsViewHolder).bind(list[position])
            }
            TYPE_CARD -> {
                (holder as CardViewHolder).bind(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class EarthViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {  //todo наследовать BaseViewHolder
        fun myBind(data: Data) {
            (ActivityRecyclerItemEarthBinding.bind(itemView)).apply {
                tvTitle.text = data.titleText
                tvDescription.text = data.description
            }
        }
    }


    class MarsViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {  //todo наследовать BaseViewHolder
        fun bind(data: Data) {
            (ActivityRecyclerItemMarsBinding.bind(itemView)).apply {
                tvTitle.text = data.titleText
            }
        }
    }


    class CardViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {  //todo наследовать BaseViewHolder
        fun bind(data: Data) {
            (ActivityRecyclerItemCardBinding.bind(itemView)).apply {
                tvName.text = data.name
                tvLastName.text = data.lastName
            }
        }
    }
}