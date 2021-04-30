package com.dendi.filmscatalogs.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dendi.filmscatalogs.BuildConfig
import com.dendi.filmscatalogs.data.source.remote.response.ListResponse
import com.dendi.filmscatalogs.databinding.FilmsItemBinding
import com.dendi.filmscatalogs.ui.detail.DetailActivity

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ContentViewHolder>() {
    private var listMovies = ArrayList<ListResponse>()

    fun setData(movies: ArrayList<ListResponse>) {
        listMovies.clear()
        listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemsMovies =
            FilmsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemsMovies)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ContentViewHolder(private val binding: FilmsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: ListResponse) {
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGES + "/${movies.posterPath}")
                .into(binding.imageItem)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, movies)
                intent.putExtra(DetailActivity.EXTRA_TYPE, "movies")
                itemView.context.startActivity(intent)
            }
        }
    }
}