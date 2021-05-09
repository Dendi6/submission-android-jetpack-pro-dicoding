package com.dendi.filmscatalogs.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dendi.filmscatalogs.BuildConfig
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.databinding.FilmsItemBinding
import com.dendi.filmscatalogs.ui.detail.DetailActivity

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ContentViewHolder>() {
    private var mData = ArrayList<ListEntity>()

    fun setData(list: List<ListEntity>) {
        mData.clear()
        mData.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemsMovies =
            FilmsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemsMovies)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class ContentViewHolder(private val binding: FilmsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ListEntity) {
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGES + "/${data.images}")
                .into(binding.imageItem)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                itemView.context.startActivity(intent)
            }
        }
    }
}