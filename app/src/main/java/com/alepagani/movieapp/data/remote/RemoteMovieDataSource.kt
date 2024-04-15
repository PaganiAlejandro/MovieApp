package com.alepagani.movieapp.data.remote

import com.alepagani.movieapp.application.AppConstants
import com.alepagani.movieapp.data.model.MovieResponse
import com.alepagani.movieapp.repository.WebService

class RemoteMovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieResponse = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRateMovies(): MovieResponse = webService.getTopRateMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieResponse = webService.getPopularMovies(AppConstants.API_KEY)
}