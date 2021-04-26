package com.dendi.filmscatalogs.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dendi.filmscatalogs.R

class MoviesFragment : Fragment() {

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MoviesViewModel::class.java]

        val rvMovies: RecyclerView = view.findViewById(R.id.rv_movies)
        val moviesAdapter = MoviesAdapter()

        rvMovies.layoutManager = LinearLayoutManager(context)
        rvMovies.setHasFixedSize(true)
        rvMovies.adapter = moviesAdapter

//        moviesViewModel.setMovies()
        moviesViewModel.listReview.observe(this, { results ->
            Log.d("Berhasil : ",results.map { it }.toString() )
        })

    }
}