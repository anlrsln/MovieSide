package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.GenreCardBinding
import com.example.movieapp.models.GenreX


class GenresAdapter(val genreList: List<GenreX>?, private val isFirstScreen : Boolean = true) : RecyclerView.Adapter<GenresAdapter.GenreCardViewHolder>() {

    inner class GenreCardViewHolder(val binding:GenreCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreCardViewHolder {
        val binding = GenreCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GenreCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreCardViewHolder, position: Int) {
        val genre = genreList?.get(position)
        holder.binding.genreText.text = genre?.name
    }

    override fun getItemCount(): Int {
        if(isFirstScreen){
            return 4
        }else{
            return genreList?.size ?: 0
        }
    }


}