package com.alepagani.movieapp.data.local

import com.alepagani.movieapp.application.AppConstants
import com.alepagani.movieapp.data.model.Movie
import com.alepagani.movieapp.data.model.MovieEntity
import com.alepagani.movieapp.data.model.MovieResponse
import com.alepagani.movieapp.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {
    suspend fun getUpcomingMovies(): List<Movie> {
        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRateMovies(): List<Movie> {
        return movieDao.getAllMovies().filter { it.movie_type == "top_rated" }.toMovieList()
    }

    suspend fun getPopularMovies(): List<Movie> {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity) {
        movieDao.saveMovie(movie)
    }
}