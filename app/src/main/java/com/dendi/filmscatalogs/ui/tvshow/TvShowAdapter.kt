package com.dendi.filmscatalogs.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dendi.filmscatalogs.data.FilmEntity
import com.dendi.filmscatalogs.databinding.FilmsItemBinding
import com.dendi.filmscatalogs.ui.detail.DetailActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ContentViewHolder>() {
    private val listTvShow = ArrayList<FilmEntity>()

    fun setTvShow(tvShow: List<FilmEntity>?) {
        if (tvShow == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShow)
    }

    inner class ContentViewHolder(private val binding: FilmsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: FilmEntity) {
            binding.titleItem.text = tvShow.title
            binding.dateItem.text = tvShow.dateRelease
            Glide.with(itemView.context)
                .load(tvShow.images)
                .into(binding.imageItem)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, tvShow)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemsTvShow = FilmsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemsTvShow)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }

    override fun getItemCount(): Int = listTvShow.size
}