package com.dendi.filmscatalogs.ui.home.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPageAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity)  {

    override fun createFragment(position: Int): Fragment {
        return HomeTabFragment.newInstance(position)
    }

    override fun getItemCount(): Int = 2
}