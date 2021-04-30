package com.dendi.filmscatalogs.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dendi.filmscatalogs.BuildConfig
import com.dendi.filmscatalogs.data.source.remote.response.ListResponse
import com.dendi.filmscatalogs.databinding.FilmsItemBinding
import com.dendi.filmscatalogs.ui.detail.DetailActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ContentViewHolder>() {
    private val listTvShow = ArrayList<ListResponse>()

    fun setTvShow(tvShow: List<ListResponse>) {
        listTvShow.clear()
        listTvShow.addAll(tvShow)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemsTvShow =
            FilmsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemsTvShow)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class ContentViewHolder(private val binding: FilmsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: ListResponse) {
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGES + "/${tvShow.posterPath}")
                .into(binding.imageItem)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, tvShow)
                intent.putExtra(DetailActivity.EXTRA_TYPE, "tv show")
                itemView.context.startActivity(intent)
            }
        }
    }
}