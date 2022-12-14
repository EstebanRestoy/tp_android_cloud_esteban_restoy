package fr.upjv.ccm.tp1.view

import android.content.Intent
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fr.upjv.ccm.tp1.databinding.ActivityRecyclerViewBinding
import fr.upjv.ccm.tp1.model.MyObjectForRecyclerView
import fr.upjv.ccm.tp1.model.Category
import fr.upjv.ccm.tp1.model.Boardgame
import fr.upjv.ccm.tp1.repository.AndroidVersionRepository
import fr.upjv.ccm.tp1.viewmodel.AndroidVersionViewModel

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var adapter: AndroidVersionAdapter
    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var viewModel: AndroidVersionViewModel

    private val BoardgameListObserver = Observer<List<MyObjectForRecyclerView>> {
        adapter.submitList(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fab: View = binding.fab
        fab.setOnClickListener { view ->
            startActivity(Intent(this, SearchBoardgameViewActivity::class.java))
            Snackbar.make(view, "Feature en cours de developpement", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }

        viewModel = ViewModelProvider(this)[AndroidVersionViewModel::class.java]

        // Create the instance of adapter
        adapter = AndroidVersionAdapter { item, view ->
            onItemClick(item, view)
        }
        // We define the style
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // We set the adapter to recycler view
        binding.recyclerView.adapter = adapter

    }


    override fun onStart() {
        super.onStart()
        viewModel.androidVersionList.observe(this, BoardgameListObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.androidVersionList.observe(this, BoardgameListObserver)
    }

    private fun onItemClick(Boardgame: Boardgame, view : View) {
        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        Toast.makeText(this, Boardgame.name, Toast.LENGTH_LONG).show()
    }

}
