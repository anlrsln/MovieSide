package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.PopularMovieCardBinding
import com.example.movieapp.models.GenreIdsBundle
import com.example.movieapp.models.Result
import com.example.movieapp.ui.fragments.home.HomeFragmentDirections

class PopularMovieAdapter(val movieList: List<Result>,private val isFirstScreen : Boolean = true) : RecyclerView.Adapter<PopularMovieAdapter.MovieCardViewHolder>()  {


    inner class MovieCardViewHolder(val binding:PopularMovieCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        val binding = PopularMovieCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        val movie = movieList[position]
        val genre = GenreIdsBundle(movie.genre_ids)
        holder.binding.cardNumber.text = "${position+1}"
        holder.binding.movieName.text = movie.title
        Glide.with(holder.binding.posterView)
            .load("https://image.tmdb.org/t/p/w342/${movie.poster_path}")
            .into(holder.binding.posterView)
        holder.binding.cardLinearLayout.setOnClickListener {
            val pass = HomeFragmentDirections.actionHomeFragment2ToMovieDetailFragment(
                genreIdsBundle = genre,
                movie = movie
            )
            Navigation.findNavController(it).navigate(pass)
        }
    }


    override fun getItemCount(): Int {
        if(isFirstScreen){
            return 4
        }else{
            return movieList.size
        }
    }


}