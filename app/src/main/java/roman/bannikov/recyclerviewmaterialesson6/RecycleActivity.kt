package roman.bannikov.recyclerviewmaterialesson6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import roman.bannikov.recyclerviewmaterialesson6.databinding.ActivityRecycleBinding

class RecycleActivity : AppCompatActivity(), OnListItemClickListener {

    private lateinit var binding: ActivityRecycleBinding
    private lateinit var adapter: RecyclerViewAdapter

    private val list = arrayListOf(
       Data("Planets", "Description Earth", "", "", TYPE_HEADER),
       Data("Earth", "Description Earth", "", "", TYPE_EARTH),
       Data("Earth", "Description Earth", null, null, TYPE_EARTH),
       Data("Mars", "Description Mars", "", "", TYPE_MARS),
       Data("Mars", "Description Mars 1", "1", "1", TYPE_MARS),
       Data("Mars", "Description Mars", null, null, TYPE_MARS),
       Data("Card", "", "Roman", "Bannikov", TYPE_CARD),
       Data("Card", "1", null, "Bannikov", TYPE_CARD),
       Data("Card", "", "Roman", null, TYPE_CARD)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Объединение свойств? Или как это называется?
        val lon = 1
        val lat = 2
        val t = 20
        //1 sposob
        // (Pair)
        val loc = Pair(lon, lat)
        // (Triple)
        val weather = Triple(lon, lat, t)
        //2 sposob
        // (Pair)
        val loc2 = lat to lon
        // (Triple)
        val weather2 = lat to lon to t
        loc2.first
        loc2.second


        adapter = RecyclerViewAdapter(this)
        adapter.setList(list)
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            onAddBtnClick(list.size)
        }

        //JUST REMEMBER!!! Чтобы все тапы по экрану обрабатывал ItemTouchHelper, а все результаты
        // передавались через колбэк в наш адаптер. (video 02:51:00)
        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.recyclerView)
        //JUST REMEMBER!!!


    }

    override fun onItemClick(data: Data) {

    }

    override fun onAddBtnClick(position: Int) {
        list.add(position, Data("Mars", "Description Mars", "", "", TYPE_MARS),)
        adapter.setAddToList(list, position)
    }

    override fun onRemoveBtnClick(position: Int) {
        list.removeAt(position)
        adapter.setRemoveToList(list, position)
    }
}