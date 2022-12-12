package fr.upjv.ccm.tp1.view

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.upjv.ccm.tp1.databinding.ActivityRecyclerViewBinding
import fr.upjv.ccm.tp1.model.MyObjectForRecyclerView
import fr.upjv.ccm.tp1.model.Category
import fr.upjv.ccm.tp1.model.Boardgame
import fr.upjv.ccm.tp1.model.Footer

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var adapter: AndroidVersionAdapter
    private lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create the instance of adapter
        adapter = AndroidVersionAdapter { item, view ->
            onItemClick(item, view)
        }
        // We define the style
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // We set the adapter to recycler view
        binding.recyclerView.adapter = adapter

        // Generate data and give it to adapter
        adapter.submitList(generateFakeData())


    }

    private fun generateFakeData(): MutableList<MyObjectForRecyclerView> {
        val result = mutableListOf<MyObjectForRecyclerView>()
        // Create data raw
        mutableListOf(
            Boardgame("Gloomhaven (2017)", 130.99, "Gloomhaven is a game of Euro-inspired tactical combat in a persistent world of shifting motives. Players will take on the role of a wandering adventurer with their own special set of skills and their own reasons for traveling to this dark corner of the world. Players must work together out of necessity to clear out menacing dungeons and forgotten ruins. In the process, they will enhance their abilities with experience and loot, discover new locations to explore and plunder, and expand an ever-branching story fueled by the decisions they make.","Expert"),
            Boardgame("Brass: Birmingham (2018) ", 6.8, "Brass: Birmingham is an economic strategy game sequel to Martin Wallace' 2007 masterpiece, Brass. Brass: Birmingham tells the story of competing entrepreneurs in Birmingham during the industrial revolution, between the years of 1770-1870.", "Advanced"),
            Boardgame("Pandemic Legacy: Season 1 (2015) ", 7.00, "dad","Family"),
            Boardgame("Ark Nova (2021) ", 8.87, "dadedaed", "Kids"),
            Boardgame("Twilight Imperium: Fourth Edition (2017) ", 9.99, "adadswaedae", "Advanced"),
            Boardgame("Terraforming Mars (2016)", 10.45, "adedadedae", "Advanced"),
            Boardgame("Gloomhaven: Jaws of the Lion (2020) ", 11.87, "dadeae","Kids"),
            Boardgame("Star Wars: Rebellion (2016) ", 12.9, "daaedadedaectggt","Expert"),
            Boardgame("War of the Ring: Second Edition (2011)" , 12.9, "dad ade dae derf","Family"),
            Boardgame("Spirit Island (2017) ", 12.9, "afrfr gtrf zrz","Family")
        ).groupBy {
            it.category
        }.forEach { (categoryName, boargames) ->
            result.add(Category(name=categoryName))
            result.addAll(boargames)
            //result.add(Footer(boargames.sumOf { it.price }))

        }
        return result
    }

    private fun onItemClick(Boardgame: Boardgame, view : View) {
        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        Toast.makeText(this, Boardgame.name, Toast.LENGTH_LONG).show()
    }

}
