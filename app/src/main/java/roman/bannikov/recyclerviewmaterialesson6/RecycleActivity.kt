package roman.bannikov.recyclerviewmaterialesson6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import roman.bannikov.recyclerviewmaterialesson6.databinding.ActivityRecycleBinding

class RecycleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = arrayListOf(
            Data("Header", null, "", "", TYPE_HEADER),
            Data("Earth", "Description Earth", "", "", TYPE_EARTH),
            Data("Earth", "Description Earth", "", "", TYPE_EARTH),
            Data("Earth", "Description Earth", null, null, TYPE_EARTH),
            Data("Mars", "Description Mars", "", "", TYPE_MARS),
            Data("Solyanka", "Just in case", "Romka", "Bant", TYPE_SOLYANKA),
            Data("Mars", "Description Mars 1", "1", "1", TYPE_MARS),
            Data("Solyanka", "Just in case", "Romka", "Bant", TYPE_SOLYANKA),
            Data("Mars", "Description Mars", null, null, TYPE_MARS),
            Data("Card", "", "Roman", "Bannikov", TYPE_CARD),

            Data("Card", "1", null, "Bannikov", TYPE_CARD),
            Data("Solyanka", "Just in case", "Romka", "Bant", TYPE_SOLYANKA),
            Data("Card", "", "Roman", null, TYPE_CARD),
            Data("Solyanka", "Just in case", "Romka", "Bant", TYPE_SOLYANKA)
        )
        binding.recyclerView.adapter = RecyclerViewAdapter(list)

    }
}