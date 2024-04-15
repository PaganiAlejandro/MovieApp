package com.alepagani.movieapp.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.alepagani.movieapp.R
import com.alepagani.movieapp.databinding.FragmentMovieDetailBinding
import com.bumptech.glide.Glide

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${args.backgroudImgUrl}")
            .centerCrop()
            .into(binding.imgBackground)


    }

}