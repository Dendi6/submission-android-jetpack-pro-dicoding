package com.dendi.filmscatalogs.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.databinding.ActivityFavoriteBinding
import com.dendi.filmscatalogs.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var adapter: FavoriteAdapter

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemTouchHelper.attachToRecyclerView(binding.rvFavorited)

        supportActionBar?.apply {
            elevation = 0f
            setDisplayHomeAsUpEnabled(true)
            title = "Favorite"
        }

        val factory = ViewModelFactory.getInstance(this)
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        adapter = FavoriteAdapter()
        binding.rvFavorited.layoutManager = GridLayoutManager(this, 3)
        binding.rvFavorited.setHasFixedSize(true)
        binding.rvFavorited.adapter = adapter

        favoriteViewModel.getFavorite().observe(this, { items ->
            if (items.isEmpty()) {
                adapter.submitList(items)
                binding.emptyImages.visibility = View.VISIBLE
            } else {
                binding.emptyImages.visibility = View.GONE
                adapter.submitList(items)
            }
        })
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val swipedPosition = viewHolder.adapterPosition
            val listEntity = adapter.getSwipedData(swipedPosition)
            listEntity?.let { favoriteViewModel.setFavorited(it) }

            val snackbar =
                Snackbar.make(binding.rvFavorited, R.string.message_undo, Snackbar.LENGTH_LONG)
            snackbar.setAction(R.string.message_ok) {
                listEntity?.let { favoriteViewModel.setFavorited(it) }
            }
            snackbar.show()
        }
    })
}