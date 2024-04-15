package com.alepagani.movieapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alepagani.movieapp.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    // Creo la instancia de Room, una unica instancia, para solo tener una instancia
    companion object {
        private var INSTANCE: AppDataBase? = null
        fun getDataBase(context: Context): AppDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "movie_table"
            ).build()

            return INSTANCE!!
        }


    }
}