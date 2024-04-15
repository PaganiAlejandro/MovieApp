package com.alepagani.movieapp.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alepagani.movieapp.core.BaseConcatHolder
import com.alepagani.movieapp.databinding.TopMoviesRowBinding
import com.alepagani.movieapp.ui.movie.adapters.MovieAdapter

class TopRatedConcatAdapter (private val movieAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = TopMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(movieAdapter)
        }
    }

    override fun getItemCount() = 1

    private inner class ConcatViewHolder(val binding: TopMoviesRowBinding): BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvTopMovies.adapter = adapter
        }
    }
}