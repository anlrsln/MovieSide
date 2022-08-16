package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.PopularMovieCardBinding
import com.example.movieapp.models.Result

class PopularMovieAdapter(val movieList: List<Result>,private val isFirstScreen : Boolean = true) : RecyclerView.Adapter<PopularMovieAdapter.MovieCardViewHolder>()  {


    inner class MovieCardViewHolder(val binding:PopularMovieCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        val binding = PopularMovieCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        val movie = movieList.get(position)
        holder.binding.movieName.text = movie.title
        holder.binding.movieGenre.text=movie.genrestring
        Glide.with(holder.binding.posterView)
            .load("https://image.tmdb.org/t/p/w342/${movie.poster_path}")
            .into(holder.binding.posterView)
    }

    override fun getItemCount(): Int {
        if(isFirstScreen){
            return 4
        }else{
            return movieList.size
        }
    }


}