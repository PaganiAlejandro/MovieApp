package com.alepagani.movieapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Movie(
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int> = listOf(),
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = 0,
    val movie_type: String = ""
)

// ROOM
@Entity
data class MovieEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String,
    // @ColumnInfo(name = "adult")
    // val genre_ids: List<Int> = listOf(),
    @ColumnInfo(name = "original_language") val original_language: String = "",
    @ColumnInfo(name = "original_title") val original_title: String = "",
    @ColumnInfo(name = "overview") val overview: String = "",
    @ColumnInfo(name = "popularity") val popularity: Double = -1.0,
    @ColumnInfo(name = "poster_path") val poster_path: String = "",
    @ColumnInfo(name = "release_date") val release_date: String = "",
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "video") val video: Boolean = false,
    @ColumnInfo(name = "vote_average") val vote_average: Double = -1.0,
    @ColumnInfo(name = "vote_count") val vote_count: Int = 0,
    @ColumnInfo(name = "movie_type") val movie_type: String = ""
)

fun List<MovieEntity>.toMovieList(): List<Movie> {
    val resultList = mutableListOf<Movie>()
    this.forEach {
        resultList.add(it.toMovie())
    }
    return resultList
}

fun MovieEntity.toMovie(): Movie = Movie(
    this.id,
    this.adult,
    this.backdrop_path,
    listOf(),
    this.original_language,
    this.original_title,
    this.overview,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.title,
    this.video,
    this.vote_average,
    this.vote_count,
    this.movie_type
)

fun Movie.toMovieEntity(type: String): MovieEntity = MovieEntity(
    this.id,
    this.adult,
    this.backdrop_path,
    this.original_language,
    this.original_title,
    this.overview,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.title,
    this.video,
    this.vote_average,
    this.vote_count,
    movie_type = type
)