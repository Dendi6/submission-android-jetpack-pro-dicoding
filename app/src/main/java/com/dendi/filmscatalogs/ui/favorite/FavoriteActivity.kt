package com.dendi.filmscatalogs.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dendi.filmscatalogs.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

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
        }
    }
}