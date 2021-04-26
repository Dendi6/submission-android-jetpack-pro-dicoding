package com.dendi.filmscatalogs.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.data.source.local.entity.FilmEntity
import com.dendi.filmscatalogs.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var result: FilmEntity

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

        val viewModel = ViewModelProvider(
            this@DetailActivity,
            ViewModelProvider.NewInstanceFactory()
        )[DetailActivityViewModel::class.java]

        val film = intent.getParcelableExtra<FilmEntity>(EXTRA_DATA) as FilmEntity
        val type = intent.getStringExtra(EXTRA_TYPE)

        setActionBarTitle(film.title)
        result = if (type == "movies") {
            viewModel.setMovieTitle(film.title)
            viewModel.getDetailMovieByTitle()
        } else {
            viewModel.setTvShowTitle(film.title)
            viewModel.getDetailTvShowByTitle()
        }

        view(result)
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
                val films = intent.getParcelableExtra<FilmEntity>(EXTRA_DATA) as FilmEntity
                share(films)
            }
        }
    }

    private fun view(filmEntity: FilmEntity) {
        Glide.with(this)
            .load(filmEntity.images)
            .into(binding.imagesDetail)

        binding.titleDetail.text = filmEntity.title
        binding.dateRelease.text = filmEntity.dateRelease
        binding.genreDuration.text = filmEntity.genre
        binding.overview.text = filmEntity.overview
    }

    private fun share(filmEntity: FilmEntity) {
        val title = filmEntity.title
        val overview = filmEntity.overview
        val textShare = getString(R.string.text_share, title, overview)
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, textShare)
        intent.type = "text/plain"

        startActivity(Intent.createChooser(intent, "Share using .."))
    }
}