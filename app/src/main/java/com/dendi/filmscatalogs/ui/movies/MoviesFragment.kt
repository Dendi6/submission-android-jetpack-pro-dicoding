package com.dendi.filmscatalogs.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dendi.filmscatalogs.R

class MoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[MoviesViewModel::class.java]
            val movies = viewModel.getMovies()

            val moviesAdapter = MoviesAdapter()
            moviesAdapter.setMovies(movies)

            val rvMovies:RecyclerView = view.findViewById(R.id.rv_movies)
            rvMovies.layoutManager = LinearLayoutManager(context)
            rvMovies.setHasFixedSize(true)
            rvMovies.adapter = moviesAdapter
        }
    }
}