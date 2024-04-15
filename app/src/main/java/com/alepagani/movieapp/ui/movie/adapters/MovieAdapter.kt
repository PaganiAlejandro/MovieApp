package com.alepagani.movieapp.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alepagani.movieapp.core.BaseViewHolder
import com.alepagani.movieapp.data.model.Movie
import com.alepagani.movieapp.databinding.MovieItemBinding
import com.bumptech.glide.Glide

class MovieAdapter(
    private val movielist: List<Movie>,
    private val itemClickListener: onMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface onMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MovieViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onMovieClick(movielist[position])
        }
        return holder
    }

    override fun getItemCount() = movielist.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MovieViewHolder -> holder.bind(movielist[position])
        }
    }

    private inner class MovieViewHolder(
        val binding: MovieItemBinding,
        val context: Context
    ) : BaseViewHolder<Movie>(binding.root) {
        override fun bind(item: Movie) {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/${item.poster_path}")
                .centerCrop()
                .into(binding.imgMovie)
        }
    }
}