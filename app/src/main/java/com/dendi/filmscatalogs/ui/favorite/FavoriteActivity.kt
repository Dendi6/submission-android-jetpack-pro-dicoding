package com.dendi.filmscatalogs.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dendi.filmscatalogs.databinding.ActivityFavoriteBinding
import com.dendi.filmscatalogs.viewmodel.ViewModelFactory

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            elevation = 0f
            setDisplayHomeAsUpEnabled(true)
            title = "Favorite"
        }

        val factory = ViewModelFactory.getInstance(this)
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        val adapter = FavoriteAdapter()
        binding.rvItems.layoutManager = GridLayoutManager(this, 3)
        binding.rvItems.setHasFixedSize(true)
        binding.rvItems.adapter = adapter

        favoriteViewModel.getFavorite().observe(this, { items ->
            if(items.isEmpty()){
                adapter.setData(items)
                binding.emptyImages.visibility = View.VISIBLE
            } else {
                adapter.setData(items)
            }
        })
    }
}