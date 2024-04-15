package com.alepagani.movieapp.repository

import com.alepagani.movieapp.data.model.Movie
import com.alepagani.movieapp.data.model.MovieResponse

interface MovieRepository {
    suspend fun getUpcomingMovies(): List<Movie>
    suspend fun getTopRateMovies(): List<Movie>
    suspend fun getPopularMovies(): List<Movie>
}

// defino los metodos para ir  a buscar los datos al server, solo mostrara la firma de los metodos