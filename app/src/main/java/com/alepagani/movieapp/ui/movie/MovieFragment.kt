package com.alepagani.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alepagani.movieapp.R
import com.alepagani.movieapp.core.Resource
import com.alepagani.movieapp.data.remote.RemoteMovieDataSource
import com.alepagani.movieapp.databinding.FragmentMovieBinding
import com.alepagani.movieapp.presentation.MovieViewModel
import com.alepagani.movieapp.presentation.MovieViewModelFactory
import com.alepagani.movieapp.repository.MovieRepositoryImpl
import com.alepagani.movieapp.repository.RetrofitClient
import 	androidx.recyclerview.widget.ConcatAdapter
import com.alepagani.movieapp.data.local.AppDataBase
import com.alepagani.movieapp.data.local.LocalMovieDataSource
import com.alepagani.movieapp.data.model.Movie
import com.alepagani.movieapp.ui.movie.adapters.MovieAdapter
import com.alepagani.movieapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.alepagani.movieapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.alepagani.movieapp.ui.movie.adapters.concat.UpcommingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.onMovieClickListener {

    private lateinit var binding: FragmentMovieBinding
    // INYECCION DE DEPENDENCIAS MANUAL
    private val viewModel by viewModels<MovieViewModel>{MovieViewModelFactory(MovieRepositoryImpl(
        RemoteMovieDataSource(RetrofitClient.webservice),
        LocalMovieDataSource(AppDataBase.getDataBase(requireContext()).movieDao())
    ))}
    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        concatAdapter = ConcatAdapter()


        viewModel.getAllMovies().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("Ale", "LOADING")
                    binding.progresssBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.d("Ale", "llego bien ${result.data.first}")
                    binding.progresssBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(UpcommingConcatAdapter(MovieAdapter(result.data.first, this@MovieFragment)))
                        addAdapter(TopRatedConcatAdapter(MovieAdapter(result.data.second, this@MovieFragment)))
                        addAdapter(PopularConcatAdapter(MovieAdapter(result.data.third, this@MovieFragment)))
                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    Log.d("Ale", "FALLO ${result.exception}")
                    binding.progresssBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        Log.d("Ale", "Peli: $movie")
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.backdrop_path,
            movie.poster_path,
            movie.overview,
            movie.vote_average.toLong(),
            movie.title,
            movie.original_language
        )
        findNavController().navigate(action)
    }
}