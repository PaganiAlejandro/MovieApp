package com.alepagani.movieapp.repository

import android.util.Log
import com.alepagani.movieapp.core.InternetCheck
import com.alepagani.movieapp.data.local.LocalMovieDataSource
import com.alepagani.movieapp.data.model.Movie
import com.alepagani.movieapp.data.model.MovieResponse
import com.alepagani.movieapp.data.model.toMovieEntity
import com.alepagani.movieapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSource: RemoteMovieDataSource,
    private val localDataSource: LocalMovieDataSource
) : MovieRepository {
    override suspend fun getUpcomingMovies(): List<Movie> {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getUpcomingMovies().results.forEach {
                localDataSource.saveMovie(it.toMovieEntity("upcoming"))
            }
            //Log.d("ALEJANDRO", localDataSource.getUpcomingMovies().toString() )
            localDataSource.getUpcomingMovies()
        } else {
            localDataSource.getUpcomingMovies()
        }
    }

    override suspend fun getTopRateMovies(): List<Movie> {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getUpcomingMovies().results.forEach {
                localDataSource.saveMovie(it.toMovieEntity("top_rated"))
            }
            //Log.d("ALEJANDRO", "TOPrated ${localDataSource.getTopRateMovies().toString()}" )
            localDataSource.getTopRateMovies()
        } else {
            localDataSource.getTopRateMovies()
        }
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getUpcomingMovies().results.forEach {
                localDataSource.saveMovie(it.toMovieEntity("popular"))
            }
            //Log.d("ALEJANDRO","POPULARES ${localDataSource.getPopularMovies().toString()}" )
            localDataSource.getPopularMovies()
        } else {
            localDataSource.getPopularMovies()
        }
    }
}