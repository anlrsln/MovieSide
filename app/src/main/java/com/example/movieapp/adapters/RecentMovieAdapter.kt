package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.RecentMovieCardBinding
import com.example.movieapp.models.GenreIdsBundle
import com.example.movieapp.models.Result
import com.example.movieapp.ui.fragments.home.HomeFragmentDirections
import java.lang.Double

class RecentMovieAdapter(val recentMovieList : List<Result>, val isFirstPage : Boolean = true ) : RecyclerView.Adapter<RecentMovieAdapter.RecentMovieCardViewHolder>(){

    inner class RecentMovieCardViewHolder(val binding : RecentMovieCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentMovieCardViewHolder {
       val binding = RecentMovieCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return RecentMovieCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentMovieCardViewHolder, position: Int) {
        val movie = recentMovieList.get(position)
        val genre = GenreIdsBundle(movie.genre_ids)

        setHolderBinding(holder,movie,genre)

    }

    override fun getItemCount(): Int {
        if (isFirstPage)
            return 4
        else
            return recentMovieList.size
    }


    fun passToDetail(genre:GenreIdsBundle,movie:Result,view:View){
        val pass = HomeFragmentDirections.actionHomeFragment2ToMovieDetailFragment(
            genreIdsBundle = genre,
            movie = movie
        )
        Navigation.findNavController(view).navigate(pass)
    }


    fun setHolderBinding(holder: RecentMovieCardViewHolder,movie:Result,genre: GenreIdsBundle){
        holder.binding.movieName.text = movie.title
        holder.binding.movieDate.text = movie.release_date
        holder.binding.movieRate.text = Double(movie.vote_average).toString()
        Glide.with(holder.binding.posterView)
            .load("https://image.tmdb.org/t/p/w342/${movie.poster_path}")
            .into(holder.binding.posterView)
        holder.binding.posterView.setOnClickListener{
            passToDetail(genre,movie,it)
        }

        holder.binding.movieName.setOnClickListener {
            passToDetail(genre,movie,it)
        }
    }

}