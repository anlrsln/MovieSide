package com.example.movieapp.viewmodels.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapters.GenresAdapter
import com.example.movieapp.adapters.PopularMovieAdapter
import com.example.movieapp.adapters.RecentMovieAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.models.Genre
import com.example.movieapp.models.GenreX
import com.example.movieapp.models.Movie
import com.example.movieapp.models.Result

class HomeViewModel : ViewModel(){


    fun settingRecyclerViewsLM(binding : FragmentHomeBinding, context: Context){
        val lmHorizontal = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        val lmVertical = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val lmHorizontalGenres = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.rvPopular.layoutManager = lmHorizontal
        binding.rvRecent.layoutManager = lmVertical
        binding.rvGenres.layoutManager = lmHorizontalGenres
    }




}