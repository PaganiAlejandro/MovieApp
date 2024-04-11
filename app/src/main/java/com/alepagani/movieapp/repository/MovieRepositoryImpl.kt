package com.alepagani.movieapp.repository

import com.alepagani.movieapp.data.model.MovieResponse
import com.alepagani.movieapp.data.remote.MovieDataSource

class MovieRepositoryImpl(private val dataSource: MovieDataSource): MovieRepository {
    override suspend fun getUpcomingMovies(): MovieResponse = dataSource.getUpcomingMovies()

    override suspend fun getTopRateMovies(): MovieResponse = dataSource.getTopRateMovies()

    override suspend fun getPopularMovies(): MovieResponse = dataSource.getPopularMovies()
}