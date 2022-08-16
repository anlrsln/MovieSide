package com.example.movieapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.data.RetrofitViewModel
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.models.Movie
import com.example.movieapp.adapters.PopularMovieAdapter
import com.example.movieapp.adapters.RecentMovieAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }




}


