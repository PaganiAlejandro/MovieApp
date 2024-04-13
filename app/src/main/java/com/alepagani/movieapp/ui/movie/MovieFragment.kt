package com.alepagani.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.alepagani.movieapp.R
import com.alepagani.movieapp.core.Resource
import com.alepagani.movieapp.data.remote.MovieDataSource
import com.alepagani.movieapp.databinding.FragmentMovieBinding
import com.alepagani.movieapp.presentation.MovieViewModel
import com.alepagani.movieapp.presentation.MovieViewModelFactory
import com.alepagani.movieapp.repository.MovieRepositoryImpl
import com.alepagani.movieapp.repository.RetrofitClient

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding
    // INYECCION DE DEPENDENCIAS MANUAL
    private val viewModel by viewModels<MovieViewModel>{MovieViewModelFactory(MovieRepositoryImpl(
        MovieDataSource(RetrofitClient.webservice)
    ))}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        viewModel.getUpcomingMovie().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("Ale", "Cargando Upcoming")
                }
                is Resource.Success -> {
                    Log.d("Ale", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("Ale", "${result.exception}")
                }
            }
        })

        viewModel.getTopRatedMovie().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("Ale", "Cargando TopRated")
                }
                is Resource.Success -> {
                    Log.d("Ale", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("Ale", "${result.exception}")
                }
            }
        })

        viewModel.getPopularesMovie().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("Ale", "Cargando Populars")
                }
                is Resource.Success -> {
                    Log.d("Ale", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("Ale", "${result.exception}")
                }
            }
        })
    }


}