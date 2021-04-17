package com.dendi.filmscatalogs.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dendi.filmscatalogs.ui.movies.MoviesFragment
import com.dendi.filmscatalogs.ui.tvshow.TvShowFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> MoviesFragment()
            1 -> TvShowFragment()
            else -> Fragment()
        }
}