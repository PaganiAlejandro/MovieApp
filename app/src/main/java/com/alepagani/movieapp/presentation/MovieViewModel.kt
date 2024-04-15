package com.alepagani.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.alepagani.movieapp.core.Resource
import com.alepagani.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo: MovieRepository): ViewModel() {

    fun getAllMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(Triple(repo.getUpcomingMovies(),repo.getTopRateMovies(),repo.getPopularMovies())))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class MovieViewModelFactory(private val repo:MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}

// Si tuviera que hacer mas de 3 lammadas, podemos crear esta clase y reemplazar Triple por esta
data class NTuple3<T1, T2, T3>(val t1: T1, val t2: T2, val t3: T3)