package com.dendi.filmscatalogs.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {
    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvTvShow: RecyclerView = view.findViewById(R.id.rv_tv_show)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
        val tvShowAdapter = TvShowAdapter()

        rvTvShow.layoutManager = GridLayoutManager(context, 3)
        rvTvShow.setHasFixedSize(true)
        rvTvShow.adapter = tvShowAdapter

        progressBar.visibility = View.VISIBLE

        val factory = ViewModelFactory.getInstance()
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        tvShowViewModel.getTvShow().observe(this, { listTvShow ->
            tvShowAdapter.setTvShow(listTvShow)
            progressBar.visibility = View.GONE
        })
    }
}