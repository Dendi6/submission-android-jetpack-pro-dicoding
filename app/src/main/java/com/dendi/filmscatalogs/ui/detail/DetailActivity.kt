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
import com.dendi.filmscatalogs.data.source.local.entity.FilmEntity
import com.dendi.filmscatalogs.data.source.remote.response.DetailResponse
import com.dendi.filmscatalogs.data.source.remote.response.ResultsMovies
import com.dendi.filmscatalogs.data.source.remote.response.ResultsTv
import com.dendi.filmscatalogs.databinding.ActivityDetailBinding

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
        detailActivityViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                DetailActivityViewModel::class.java
            )
        showLoading(true)


        if (type == "movies") {
            val film = intent.getParcelableExtra<ResultsMovies>(EXTRA_DATA) as ResultsMovies
            setActionBarTitle(film.title)
            detailActivityViewModel.setDetailMovies(film.id)
        } else {
            val film = intent.getParcelableExtra<ResultsTv>(EXTRA_DATA) as ResultsTv
            setActionBarTitle(film.title)
            detailActivityViewModel.setDetailTv(film.id)
        }

        detailActivityViewModel.getDetail().observe(this, { dataDetail ->
            view(dataDetail)
            showLoading(false)
        })
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
//                val films = intent.getParcelableExtra<FilmEntity>(EXTRA_DATA) as FilmEntity
//                share(films)
            }
        }
    }

    private fun view(movies: DetailResponse) {
        Glide.with(this)
            .load(BuildConfig.IMAGES + "/${movies.backdropPath}")
            .into(binding.imagesDetail)

        val text: String
        val type = intent.getStringExtra(EXTRA_TYPE)

        text = if (type == "movies") {
            "${movies.title} ( ${movies.voteAverage} )"
        } else {
            "${movies.name} ( ${movies.voteAverage} )"
        }

        binding.titleDetail.text = text
        binding.overview.text = movies.overview
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