package roman.bannikov.recyclerviewmaterialesson6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import roman.bannikov.recyclerviewmaterialesson6.databinding.ActivityRecyclerItemEarthBinding

const val TYPE_MARS = 2
const val TYPE_EARTH = 1


class RecyclerViewAdapter(private var list: List<Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ActivityRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context))
        return EarthViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class EarthViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    class MarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}