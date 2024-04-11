package com.alepagani.movieapp.repository

import com.alepagani.movieapp.application.AppConstants
import com.alepagani.movieapp.data.model.MovieResponse
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("apy_key") apyKey: String): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRateMovies(@Query("apy_key") apyKey: String): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("apy_key") apyKey: String): MovieResponse
}

object RetrofitClient {
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }
}