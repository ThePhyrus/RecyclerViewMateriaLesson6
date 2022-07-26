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
    }
}