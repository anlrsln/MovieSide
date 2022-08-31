package com.example.movieapp.viewmodels.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentHomeBinding

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