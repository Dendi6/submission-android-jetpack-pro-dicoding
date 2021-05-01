package com.dendi.filmscatalogs.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dendi.filmscatalogs.BuildConfig
import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.databinding.ActivityDetailBinding
import com.dendi.filmscatalogs.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailActivityViewModel: DetailActivityViewModel

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            elevation = 0f
            setDisplayHomeAsUpEnabled(true)
        }

        val type = intent.getStringExtra(EXTRA_TYPE)
        val factory = ViewModelFactory.getInstance(this)
        val film = intent.getParcelableExtra<ListEntity>(EXTRA_DATA) as ListEntity
        showLoading(true)

        detailActivityViewModel =
            ViewModelProvider(this, factory)[DetailActivityViewModel::class.java]

        if (type == "movies") {
            setActionBarTitle(film.title.toString())
            detailActivityViewModel.setSelectedFilm(film.id)
            view(detailActivityViewModel.getMovies())
        } else {
            setActionBarTitle(film.name.toString())
            detailActivityViewModel.setSelectedFilm(film.id)
            view(detailActivityViewModel.getTvShow())
        }

        showLoading(false)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
            binding.imagesDetail.visibility = View.GONE
            binding.titleDetail.visibility = View.GONE
            binding.overview.visibility = View.GONE
            binding.text.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.imagesDetail.visibility = View.VISIBLE
            binding.titleDetail.visibility = View.VISIBLE
            binding.overview.visibility = View.VISIBLE
            binding.text.visibility = View.VISIBLE
        }
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            this.title = title
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.share -> {
                val films = intent.getParcelableExtra<ListEntity>(EXTRA_DATA) as ListEntity
                share(films)
            }
        }
    }

    private fun view(movies: ListEntity) {
        Glide.with(this)
            .load(BuildConfig.IMAGES + "/${movies.poster}")
            .into(binding.imagesDetail)

        val type = intent.getStringExtra(EXTRA_TYPE)

        val text: String = if (type == "movies") {
            "${movies.title}"
        } else {
            "${movies.name}"
        }

        binding.titleDetail.text = text
        binding.overview.text = movies.overview
    }

    private fun share(listEntity: ListEntity) {
        val type = intent.getStringExtra(EXTRA_TYPE)

        val title = if (type == "movies") {
            listEntity.title
        } else {
            listEntity.name
        }

        val overview = listEntity.overview
        val textShare = getString(R.string.text_share, title, overview)
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, textShare)
        intent.type = "text/plain"

        startActivity(Intent.createChooser(intent, "Share using .."))
    }
}