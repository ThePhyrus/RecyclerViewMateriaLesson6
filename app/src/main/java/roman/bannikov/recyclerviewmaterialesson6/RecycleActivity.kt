package roman.bannikov.recyclerviewmaterialesson6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import roman.bannikov.recyclerviewmaterialesson6.databinding.ActivityRecycleBinding

class RecycleActivity : AppCompatActivity(), OnListItemClickListener {

    private lateinit var binding: ActivityRecycleBinding
    private lateinit var adapter: RecyclerViewAdapter

    private val list = arrayListOf(
        Data("Earth", "Description Earth", "", "", TYPE_EARTH),
        Data("Earth", "Description Earth", "", "", TYPE_EARTH),
        Data("Earth", "Description Earth", null, null, TYPE_EARTH),
        Data("Mars", "Description Mars", "", "", TYPE_MARS),
        Data("Mars", "Description Mars 1", "1", "1", TYPE_MARS),
        Data("Mars", "Description Mars", null, null, TYPE_MARS),
        Data("Card", "", "Roman", "Bannikov", TYPE_CARD),
        Data("Card", "1", null, "Bannikov", TYPE_CARD),
        Data("Card", "", "Roman", null, TYPE_CARD),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = RecyclerViewAdapter(this)
        adapter.setList(list)
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            onAddBtnClick(list.size)
        }

    }

    override fun onItemClick(data: Data) {
        TODO("Not yet implemented")
    }

    override fun onAddBtnClick(position: Int) {
        list.add(position, Data("Mars", "Description Mars 1", "1", "1", TYPE_MARS))
        adapter.setAddToList(list, position)
    }

    override fun onRemoveBtnClick(position: Int) {
        list.removeAt(position)
        adapter.setRemoveToList(list, position)
    }
}