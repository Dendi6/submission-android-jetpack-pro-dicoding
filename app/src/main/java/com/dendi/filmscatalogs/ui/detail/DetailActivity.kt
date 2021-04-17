package com.dendi.filmscatalogs.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.data.FilmEntity
import com.dendi.filmscatalogs.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding

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

        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val film = intent.getParcelableExtra<FilmEntity>(EXTRA_DATA) as FilmEntity
        setActionBarTitle(film.title)
        view(film)
    }

    private fun setActionBarTitle(title:String){
        if(supportActionBar != null){
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

    private fun view(filmEntity: FilmEntity){
        Glide.with(this)
            .load(filmEntity.images)
            .into(binding.imagesDetail)

        val year = filmEntity.year
        val title = filmEntity.title
        val titleYears = getString(R.string.title_detail,title,year)
        binding.titleDetail.text = titleYears
        binding.dateRelease.text = filmEntity.dateRelease
        val duration = filmEntity.duration
        val genre = filmEntity.genre
        val durationGenreText = getString(R.string.genre_duration,genre,duration)
        binding.genreDuration.text = durationGenreText
        binding.overview.text = filmEntity.overview
    }

    private fun share(filmEntity: FilmEntity){
        val title = filmEntity.title
        val overview = filmEntity.overview
        val textShare = getString(R.string.text_share,title,overview)
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, textShare)
        intent.type = "text/plain"

        startActivity(Intent.createChooser(intent, "Share using .."))
    }
}