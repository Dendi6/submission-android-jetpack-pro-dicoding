package com.dendi.filmscatalogs.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.viewmodel.ViewModelFactory
import com.dendi.filmscatalogs.vo.Status

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

        val factory = ViewModelFactory.getInstance(requireActivity())
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        tvShowViewModel.getTvShow().observe(this, { listTvShow ->
            if (listTvShow != null) {
                when (listTvShow.status) {
                    Status.LOADING -> progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        listTvShow.data?.let { tvShowAdapter.setTvShow(it) }
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}