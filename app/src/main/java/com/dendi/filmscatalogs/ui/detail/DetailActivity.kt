package com.dendi.filmscatalogs.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dendi.filmscatalogs.BuildConfig
import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.data.source.local.entity.DetailEntity
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.databinding.ActivityDetailBinding
import com.dendi.filmscatalogs.viewmodel.ViewModelFactory
import com.dendi.filmscatalogs.vo.Status

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailActivityViewModel: DetailActivityViewModel

    private var menu: Menu? = null

    companion object {
        const val EXTRA_DATA = "extra_data"
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

        val factory = ViewModelFactory.getInstance(this)
        val film = intent.getParcelableExtra<ListEntity>(EXTRA_DATA) as ListEntity

        detailActivityViewModel =
            ViewModelProvider(this, factory)[DetailActivityViewModel::class.java]
        detailActivityViewModel.setSelectedFilm(film.id)

        if (film.type == "movies") {
            setActionBarTitle(film.title.toString())
            detailActivityViewModel.getMovies().observe(this, { detailMovies ->
                if (detailMovies != null) {
                    when (detailMovies.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showLoading(false)
                            detailMovies.data?.let { view(it) }
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        } else {
            setActionBarTitle(film.name.toString())
            detailActivityViewModel.getTvShow().observe(this, { detailTv ->
                if (detailTv != null) {
                    when (detailTv.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showLoading(false)
                            detailTv.data?.let { view(it) }
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
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

        this.menu = menu
        val films = intent.getParcelableExtra<ListEntity>(EXTRA_DATA) as ListEntity
        setBookmarkState(films.favorited)

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
            R.id.action_bookmark -> {
                val films = intent.getParcelableExtra<ListEntity>(EXTRA_DATA) as ListEntity
                val newState = !films.favorited

                detailActivityViewModel.setFavorite(films, newState)
                message(newState)
                setBookmarkState(newState)
            }
        }
    }

    private fun view(data: DetailEntity) {
        Glide.with(this)
            .load(BuildConfig.IMAGES + "/${data.poster}")
            .into(binding.imagesDetail)

        val film = intent.getParcelableExtra<ListEntity>(EXTRA_DATA) as ListEntity

        val text: String = if (film.type == "movies") {
            "${data.title}"
        } else {
            "${data.name}"
        }

        binding.titleDetail.text = text
        binding.overview.text = data.overview
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_24)
        } else {
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_border_24)
        }
    }

    private fun message(state: Boolean){
        if (state){
            Toast.makeText(this, "Favorited", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun share(listEntity: ListEntity) {
        val title = if (listEntity.type == "movies") {
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